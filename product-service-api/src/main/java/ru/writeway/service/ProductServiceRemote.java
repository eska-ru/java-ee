package ru.writeway.service;

import java.util.List;

public interface ProductServiceRemote {

    List<ProductRepr> findAll();

    ProductRepr findById(Long id);

    Long countAll();
}
