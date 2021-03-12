package ru.writeway.persist;

import ru.writeway.service.ProductRepr;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAll", query = "from Product"),
        @NamedQuery(name = "countAll", query = "select count(*) from Product"),
        @NamedQuery(name = "deleteById", query = "delete from Product p where p.id = :id"),
        @NamedQuery(name = "getProductById", query = "from Product where id = :id"),
        @NamedQuery(name = "getAllByCategoryId", query = "from Product where category.id = :id"),
        @NamedQuery(name = "getAllByName", query = "from Product where name = :name")
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(name = "price")
    protected BigDecimal price;

    @Column
    protected String description;

    @Column
    protected String name;

    @Column
    protected int stock;

    @ManyToOne()
    protected Category category;

    public Product(ProductRepr product, Category category) {
        this(product.getId(), product.getName(), product.getDescription(), product.getPrice(), product.getStock());
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public Product(Product product, Category category) {
        this(product.id, product.name, product.description, product.price, product.stock);
        this.category = category;
    }

    public Product() {
        this(null, "", "", new BigDecimal(0), 0);
    }

}
