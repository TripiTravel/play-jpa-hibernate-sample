package models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "orders")
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = OrderItem.class, mappedBy = "orders")
    private Set<OrderItem> orderItems;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(targetEntity = Transaction.class, mappedBy = "orders")
    private Set<Transaction> transactions;
}
