package ru.writeway.persist;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            ut.begin();

            try {
                saveOrUpdate(new Category("Фрукты"));
                saveOrUpdate(new Category("Овощи"));
                saveOrUpdate(new Category("Бакалея"));
                ut.commit();
            } catch (Exception e) {
                ut.rollback();
            }
        }
    }

    public Long countAll() {
        return em.createNamedQuery("countAllCategory", Long.class)
                .getSingleResult();
    }

    public Category getById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        return em.createNamedQuery("getCategoryById", Category.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    public List<Category> findAll() {
        return em.createNamedQuery("findAllCategory", Category.class).getResultList();
    }

    @Transactional
    public void saveOrUpdate(Category category) {
        System.out.println("Saving category: " + category.name);
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    @Transactional
    public void delete(Category category) {
        em.remove(category);
    }
}
