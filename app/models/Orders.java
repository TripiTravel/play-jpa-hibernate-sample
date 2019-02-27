package models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = OrderItem.class, mappedBy = "orders")
    private List<OrderItem> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
