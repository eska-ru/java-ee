package ru.writeway.persist;

import java.math.BigDecimal;

public class Product {
    protected Long id;
    protected BigDecimal price;
    protected String description;
    protected String name;
    protected int stock;

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

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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

    public Product(Long id, String name, String description, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
        this(null, "", "", new BigDecimal(0), 0);
    }

}
