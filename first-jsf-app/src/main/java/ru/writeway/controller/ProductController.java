package ru.writeway.controller;

import ru.writeway.persist.Product;
import ru.writeway.persist.TemporaryProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private TemporaryProductRepository productRepository;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect-true";
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect-true";
    }

    public String saveProduct() {
        productRepository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect-true";
    }

    public void deleteProduct(Product product) {
        productRepository.deleteById(product.getId());
    }
}
