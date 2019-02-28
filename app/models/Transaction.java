package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "transactions")
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {


    @Id
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "transaction_code")
    private String transactionCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private Orders orders;
}
