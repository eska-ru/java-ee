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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class SqlProductRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            ut.begin();

            try {
                saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", new BigDecimal(100), 10));
                saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", new BigDecimal(200), 5));
                saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", new BigDecimal(200), 1));
                ut.commit();
            } catch (Exception e) {
                ut.rollback();
            }
        }
    }

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

    @Transactional
    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
