package ru.writeway.persist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class TemporaryProductRepository extends ProductRepository{
    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    public TemporaryProductRepository() {
        saveOrUpdate(new Product(null, "Product  1",
                "Description of product 1", new BigDecimal(100)));
        saveOrUpdate(new Product(null, "Product  2",
                "Description of product 2", new BigDecimal(200)));
        saveOrUpdate(new Product(null, "Product  3",
                "Description of product 3", new BigDecimal(200)));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product findById(Long id) {
        return productMap.get(id);
    }

    @Override
    public void saveOrUpdate(Product product) {
        if (product == null) {
            return;
        }

        if (product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    @Override
    public Boolean deleteById(Long id) {
        if (!productMap.containsKey(id)) {
            return false;
        }
        productMap.remove(id);
        return true;
    }
}
