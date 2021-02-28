package ru.writeway.controller;

import ru.writeway.persist.Product;
import ru.writeway.persist.SqlProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@Named
@SessionScoped
public class CartController implements Serializable {

    @Inject
    private SqlProductRepository productRepository;

    private Map<Product, Integer> productMap = new HashMap<>();

    public Boolean addToCart(Product product) {
        if (product == null) {
            return false;
        }

        if (!product.getFromStock()) {
            return false;
        }

        if (productMap.containsKey(product)) {
            productMap.replace(product, productMap.get(product)+1);
        } else {
            productMap.put(product, 1);
        }

        return true;
    }

    public void removeFromCart(Product product) {
        if (product == null) {
            return;
        }

        if (productMap.containsKey(product)) {
            product.putToStock();
            productMap.replace(product, productMap.get(product)-1);

            if (productMap.get(product) < 1) {
                productMap.remove(product);
            }
        }
    }

    public void removeAll(Product product) {
        if (product == null) {
            return;
        }

        if (productMap.containsKey(product)) {
            for (int i = 0; i < productMap.get(product); i++) {
                product.putToStock();
            }
        }

        productMap.remove(product);
    }

    public List<Map.Entry<Product, Integer>> getAllProducts() {
        Set<Map.Entry<Product, Integer>> productSet = productMap.entrySet();
        return new ArrayList<>(productSet);
    }
}
