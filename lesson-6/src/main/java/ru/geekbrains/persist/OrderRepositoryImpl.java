package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.utils.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Override
    public Order findById(long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll() {
        return em.createQuery("select t from Order t", Order.class)
                 .getResultList();
    }

    @Override
    @TransactionAttribute
    public void insert(Order order) {
        em.persist(order);
    }

    @Override
    @TransactionAttribute
    public void update(Order order) {
        em.merge(order);
    }

    @Override
    @Interceptors({Logger.class})
    public List<ProductRepr> getOrderProducts(Order order) {
        Long id = order.getId();
        return em
                .createQuery(
                        "select new ru.geekbrains.service.ProductRepr(p.id, p.description, p.price, c) from OrdersProducts op" +
                        " left join Product p " +
                        "on p.id = op.product.id " +
                        " left join Category c " +
                        "on p.category.id = c.id " +
                        "where op.order.id = :id",
                        ProductRepr.class
                )
                .setParameter("id", id)
                .getResultList();

    }

    @Override
    @TransactionAttribute
    public void delete(long id) {
        Order order = em.find(Order.class, id);
        if (order != null) {
            em.remove(order);
        }
    }
}
