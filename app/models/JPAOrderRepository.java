package models;

import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Provide JPA operations running inside of a thread pool sized to the connection pool
 */
public class JPAOrderRepository implements OrderRepository {

    private final JPAApi jpaApi;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public JPAOrderRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<Orders> add(Orders person) {
        return supplyAsync(() -> wrap(em -> insert(em, person)), executionContext);
    }

    @Override
    public CompletionStage<Stream<Orders>> list() {
        return supplyAsync(() -> wrap(em -> list(em)), executionContext);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Orders insert(EntityManager em, Orders person) {
        em.persist(person);
        return person;
    }

    private Stream<Orders> list(EntityManager em) {
        List<Orders> persons = em.createQuery("select o from orders o " +
                "join fetch o.transactions s " +
                "join fetch o.orderItems oi " +
                "where o.id > 749767 AND s.orderId = o.id AND oi.orderId = o.id", Orders.class).getResultList();
        return persons.stream();
    }
}
