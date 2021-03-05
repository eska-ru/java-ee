package ru.writeway.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import java.util.*;

@Stateful
public class CartServiceImpl implements CartService {

    private final Map<Long, Integer> productMap = new HashMap<>();

    @EJB
    private ProductService productService;

    @Override
    public Boolean addToCart(Long id) {
        if (id == null) {
            return false;
        }

        ProductRepr product = productService.getById(id);

        if (!product.getFromStock()) {
            return false;
        }

        productService.saveOrUpdate(product);

        if (productMap.containsKey(product.getId())) {
            productMap.replace(product.getId(), productMap.get(product.getId())+1);
        } else {
            productMap.put(product.getId(), 1);
        }

        return true;
    }

    @Override
    public void removeFromCart(Long id) {
        if (id == null) {
            return;
        }

        if (productMap.containsKey(id)) {
            ProductRepr product = productService.getById(id);
            product.putToStock();
            productService.saveOrUpdate(product);

            productMap.replace(product.getId(), productMap.get(product.getId())-1);
            if (productMap.get(product.getId()) < 1) {
                productMap.remove(product.getId());
            }
        }
    }

    @Override
    public void removeAll(Long id) {
        if (id == null) {
            return;
        }

        if (productMap.containsKey(id)) {
            ProductRepr product = productService.getById(id);

            for (int i = 0; i < productMap.get(id); i++) {
                product.putToStock();
            }
            productService.saveOrUpdate(product);
        }

        productMap.remove(id);
    }

    @Override
    public List<Map.Entry<Long, Integer>> getAllProducts() {
        Set<Map.Entry<Long, Integer>> productSet = productMap.entrySet();
        return new ArrayList<>(productSet);
    }
}
