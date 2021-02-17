package ru.writeway.persist;

import java.util.List;

abstract public class ProductRepository {
    abstract public List<Product> findAll();
    abstract public Product findById(Long id);
    abstract public void saveOrUpdate(Product product);
    abstract public Boolean deleteById(Long id);
}
