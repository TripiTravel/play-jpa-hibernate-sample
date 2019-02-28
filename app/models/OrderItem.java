package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "orders_items")
@Getter @Setter
public class OrderItem {

    @Id
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_type")
    private String productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false, insertable = false, updatable = false)
    private Orders orders;
}
