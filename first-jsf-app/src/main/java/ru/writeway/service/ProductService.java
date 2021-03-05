package ru.writeway.service;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<ProductRepr> findAll();

    ProductRepr getById(Long id);

    Long countAll();

    void saveOrUpdate(ProductRepr product);

    void deleteById(Long id);

    public boolean getFromStock(ProductRepr product);

    public void putToStock(ProductRepr product);
}
