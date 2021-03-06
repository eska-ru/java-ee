package ru.writeway.service;

import javax.ejb.Local;
import java.util.List;
import java.util.Map;

@Local
public interface CartService {

    public Boolean addToCart(Long id);

    public void removeFromCart(Long id);

    public void removeAll(Long id);

    public List<Map.Entry<Long, Integer>> getAllProducts();
}
