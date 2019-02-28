package models;

import javax.persistence.*;

@Entity(name = "transactions")
@Table(name = "transactions")
public class Transaction {


    @Id
    private Long id;

    @Column
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", referencedColumnName = "id", nullable=false, insertable = false, updatable = false)
    private Orders orders;
}
