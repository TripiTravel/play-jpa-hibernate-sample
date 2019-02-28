package models;

import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(fetch = FetchType.LAZY, targetEntity = Transaction.class, mappedBy = "orders")
    private List<Transaction> transactions;
}
