package ru.writeway.controller;

import org.primefaces.PrimeFaces;
import ru.writeway.persist.Category;
import ru.writeway.persist.CategoryRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    private List<Category> categories;

    public void preloadData(ComponentSystemEvent e) {
        categories = categoryRepository.findAll();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void createCategory() {
        this.category = new Category();
    }

    public List<Category> getAllCategories() {
        return categories;
    }

    public void saveCategory() {
        categoryRepository.saveOrUpdate(category);
        PrimeFaces.current().dialog().closeDynamic("category_add_form");
    }

    public void addCategoryDialog() {
        this.category = new Category();

        Map<String, Object> options = new HashMap<>();
        options.put("modal", true);
        options.put("closeOnEscape", true);

        PrimeFaces.current().dialog().openDynamic("category_add_form", options, null);
    }

    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }
}
