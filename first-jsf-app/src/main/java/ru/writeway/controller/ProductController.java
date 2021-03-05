package ru.writeway.controller;

import ru.writeway.persist.Category;
import ru.writeway.persist.CategoryRepository;
import ru.writeway.persist.Product;
import ru.writeway.service.ProductRepr;
import ru.writeway.service.ProductService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @EJB
    private ProductService productService;

    @EJB
    private CategoryRepository categoryRepository;

    private ProductRepr product;

    private List<ProductRepr> products;

    public void preloadData() {
        products = productService.findAll();
    }

    public List<Category> getCategories() {
        categories = categoryRepository.findAll();
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    private List<Category> categories;

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public String createProduct() {
        this.product = new ProductRepr();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public ProductRepr getProductById(Long id) {
        for (ProductRepr productRepr : products) {
            if (productRepr.getId().equals(id)) {
                return productRepr;
            }
        }

        return null;
    }

    public List<ProductRepr> getAllProducts() {
        return products;
    }

    public String editProduct(ProductRepr product) {
        this.product = product;
        return "/product_form.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        productService.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) {
        productService.deleteById(product.getId());
    }
}
