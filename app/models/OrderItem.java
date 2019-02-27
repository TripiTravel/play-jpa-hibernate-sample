package models;

import javax.persistence.*;

@Entity(name = "orders_items")
public class OrderItem {

    @Id
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id", nullable=false, insertable = false, updatable = false)
    private Orders orders;
}
