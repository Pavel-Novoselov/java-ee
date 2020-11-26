package ru.geekbrains.persist;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Named
@ApplicationScoped
public class OrderRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public Order findById(long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("select t from Order t", Order.class)
                 .getResultList();
    }

    @Transactional
    public void insert(Order order) {
        em.persist(order);
    }

    @Transactional
    public void update(Order order) {
        em.merge(order);
    }

    @Transactional
    public void delete(long id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
        }
    }
}
