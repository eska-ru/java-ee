package ru.writeway.controller;

import ru.writeway.service.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public Boolean addToCart(Long id) {
        return cartService.addToCart(id);
    }

    public void removeFromCart(Long id) {
        cartService.removeFromCart(id);
    }

    public void removeAll(Long id) {
        cartService.removeAll(id);
    }

    public List<Map.Entry<Long, Integer>> getAllProducts() {
        return cartService.getAllProducts();
    }
}
