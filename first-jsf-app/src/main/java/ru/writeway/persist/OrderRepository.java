package ru.writeway.persist;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class OrderRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            ut.begin();

            try {
                saveOrUpdate(new Order());
                saveOrUpdate(new Order());
                saveOrUpdate(new Order());
                ut.commit();
            } catch (Exception e) {
                ut.rollback();
            }
        }
    }

    public Long countAll() {
        return em.createNamedQuery("countAllOrders", Long.class)
                .getSingleResult();
    }

    public Order getById(Long id) {
        if (id == null) {
            throw new NullPointerException();
        }

        return em.createNamedQuery("getOrderById", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        }
        em.merge(order);
    }

    public List<Order> findAll() {
        return em.createNamedQuery("findAllOrders", Order.class).getResultList();
    }

    @Transactional
    public void delete(Order order) {
        em.remove(order);
    }
}
