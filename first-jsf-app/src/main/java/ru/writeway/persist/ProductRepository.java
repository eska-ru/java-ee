package ru.writeway.persist;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public Long countAll() {
        return em.createNamedQuery("countAll", Long.class)
                .getSingleResult();
    }

    public Product getById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        return em.createNamedQuery("getProductById", Product.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAll", Product.class)
                .getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public boolean saveOrUpdate(Long productId) {
        Product product = getById(productId);
        if (product == null) {
            return false;
        }
        saveOrUpdate(product);
        return true;
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
