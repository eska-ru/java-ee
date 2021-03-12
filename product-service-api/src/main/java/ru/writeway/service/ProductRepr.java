package ru.writeway.service;

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

    public ProductRepr(Long id, String name, String description, BigDecimal price, Long categoryId,
                       String categoryName, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.stock = stock;
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
