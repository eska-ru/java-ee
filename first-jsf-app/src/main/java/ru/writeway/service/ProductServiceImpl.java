package ru.writeway.service;

import ru.writeway.persist.Category;
import ru.writeway.persist.CategoryRepository;
import ru.writeway.persist.Product;
import ru.writeway.persist.ProductRepository;
import ru.writeway.rest.ProductServiceRest;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;


@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductServiceRest {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(this::buildProductRepr)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRepr findById(Long id) {
        Product product = productRepository.getById(id);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    private ProductRepr buildProductRepr(Product product) {
        ProductRepr repr = new ProductRepr();

        repr.setId(product.getId());
        repr.setName(product.getName());
        repr.setDescription(product.getDescription());
        repr.setPrice(product.getPrice());
        repr.setStock(product.getStock());
        Category category = product.getCategory();
        repr.setCategoryId(category != null ? category.getId() : null);
        repr.setCategoryName(category != null ? category.getName() : null);

        return repr;
    }

    @Override
    public ProductRepr getById(Long id) {
        Product product = productRepository.getById(id);
        if (product != null) {
            return buildProductRepr(product);
        }
        return null;
    }

    @Override
    public List<ProductRepr> getAllByCategoryId(Long id) {
        return productRepository.getAllByCategoryId(id).stream()
                .map(this::buildProductRepr)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductRepr> getAllByName(String name) {
        return productRepository.getAllByName(name).stream()
                .map(this::buildProductRepr)
                .collect(Collectors.toList());
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @TransactionAttribute
    @Override
    public void insert(ProductRepr product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
    }

    @TransactionAttribute
    @Override
    public void update(ProductRepr product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException();
        }
        saveOrUpdate(product);
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
