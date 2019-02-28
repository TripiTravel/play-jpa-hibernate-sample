package controllers;

import models.OrderItem;
import models.OrderRepository;
import models.Orders;
import models.Transaction;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static play.libs.Json.toJson;

/**
 * The controller keeps all database operations behind the repository, and uses
 * {@link play.libs.concurrent.HttpExecutionContext} to provide access to the
 * {@link play.mvc.Http.Context} methods like {@code request()} and {@code flash()}.
 */
public class PersonController extends Controller {

    private final OrderRepository personRepository;
    private final HttpExecutionContext ec;

    @Inject
    public PersonController(OrderRepository personRepository, HttpExecutionContext ec) {
        this.personRepository = personRepository;
        this.ec = ec;
    }

    public CompletionStage<Result> index() {

        return personRepository
                .list()
                .thenApplyAsync(personStream -> {
                    List<Orders> orders = personStream.collect(Collectors.toList());
                    for (Orders order : orders) {
                        for (Transaction t : order.getTransactions()) {
                            System.out.println(t.getTransactionCode());
                        }
                        for (OrderItem oi : order.getOrderItems()) {
                            System.out.println(oi.getProductType());
                        }
                    }
                    return ok("SUCCESS");
                }, ec.current());
    }

}
