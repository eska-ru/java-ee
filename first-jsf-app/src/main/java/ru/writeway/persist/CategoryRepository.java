package ru.writeway.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

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

    public void saveOrUpdate(Category category) {
        logger.info("Saving category: {}", category.name);
        if (category.getId() == null) {
            em.persist(category);
        }
        em.merge(category);
    }

    public void delete(Category category) {
        em.remove(category);
    }

    public Category getReference(Long id) {
        return em.getReference(Category.class, id);
    }
}
