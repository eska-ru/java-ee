package ru.writeway.controller;

import ru.writeway.persist.Category;
import ru.writeway.persist.CategoryRepository;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CategoryController implements Serializable {
    @EJB
    private CategoryRepository categoryRepository;

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void createCategory() {
        category = new Category();
    }

    public void saveCategory() {
        categoryRepository.saveOrUpdate(category);
    }
}
