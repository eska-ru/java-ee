package ru.writeway.persist;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "findAllCategory", query = "from Category"),
        @NamedQuery(name = "countAllCategory", query = "select count(*) from Category"),
        @NamedQuery(name = "getCategoryById", query = "from Category where id = :id")
})
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column
    protected String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
