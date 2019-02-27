package models;

import com.google.inject.ImplementedBy;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

/**
 * This interface provides a non-blocking API for possibly blocking operations.
 */
@ImplementedBy(JPAOrderRepository.class)
public interface OrderRepository {

    CompletionStage<Orders> add(Orders person);

    CompletionStage<Stream<Orders>> list();
}
