package ru.writeway.service;

import ru.writeway.persist.Category;
import ru.writeway.persist.CategoryRepository;
import ru.writeway.rest.CategoryServiceRest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Stateless
public class CategoryServiceImpl implements CategoryServiceRest {

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.getById(id);
    }

    @TransactionAttribute
    @Override
    public void insert(Category category) {
        if (category == null || category.getId() != null) {
            throw new IllegalArgumentException();
        }
        categoryRepository.saveOrUpdate(category);
    }
}
