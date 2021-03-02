package ru.writeway.controller;

import ru.writeway.persist.Order;
import ru.writeway.persist.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class OrderController implements Serializable {

    @Inject
    private OrderRepository orderRepository;

    private Order order;

    private List<Order> orders;

    public void preloadData(ComponentSystemEvent e) {
        orders = orderRepository.findAll();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public void editOrder(Order order) {
        this.order = order;
    }

    public void saveOrder() {
        orderRepository.saveOrUpdate(order);
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }
}
