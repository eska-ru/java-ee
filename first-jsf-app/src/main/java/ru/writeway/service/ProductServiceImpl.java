package ru.writeway.service;

import ru.writeway.persist.Category;
import ru.writeway.persist.CategoryRepository;
import ru.writeway.persist.Product;
import ru.writeway.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new).collect(Collectors.toList());
    }

    @Override
    public ProductRepr getById(Long id) {
        Product product = productRepository.getById(id);
        if (product != null) {
            return new ProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void saveOrUpdate(ProductRepr product) {
        Category category = null;
        if (product.getCategoryId() != null) {
            category = categoryRepository.getReference(product.getCategoryId());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean getFromStock(ProductRepr product) {
        return product.getFromStock();
    }

    @Override
    public void putToStock(ProductRepr product) {
        product.putToStock();
    }
}
