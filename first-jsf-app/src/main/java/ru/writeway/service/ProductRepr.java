package ru.writeway.service;

import ru.writeway.persist.Category;
import ru.writeway.persist.Product;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRepr implements Serializable {

    protected Long id;
    protected BigDecimal price;
    protected String description;
    protected String name;
    protected int stock;
    protected Long categoryId;
    protected String categoryName;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        if (product == null) {
            throw new NullPointerException();
        }

        id = product.getId();
        price = product.getPrice();
        description = product.getDescription();
        name = product.getName();
        stock = product.getStock();
        Category category = product.getCategory();
        categoryId = category != null ? category.getId() : null;
        categoryName = category != null ? category.getName() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean getFromStock() {
        if (stock > 0) {
            --stock;
            return true;
        }

        return false;
    }

    public void putToStock() {
        ++stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
