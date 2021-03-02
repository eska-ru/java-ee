package ru.writeway.persist;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "product_order")
@NamedQueries({
        @NamedQuery(name = "countAllOrders", query = "select count(*) from Order"),
        @NamedQuery(name = "findAllOrders", query = "from Order"),
        @NamedQuery(name = "getOrderById", query = "from Order where id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Boolean getCarried() {
        return carried;
    }

    public void setCarried(Boolean carried) {
        this.carried = carried;
    }

    @Column
    protected Boolean carried = false;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
