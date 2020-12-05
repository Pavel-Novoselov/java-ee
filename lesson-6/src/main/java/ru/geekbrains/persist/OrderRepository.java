package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepr;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Local
public interface OrderRepository {
    public Order findById(long id);

    public List<Order> findAll() ;


    @TransactionAttribute
    public void insert(Order order);

    @TransactionAttribute
    public void update(Order order);

    @TransactionAttribute
    public void delete(long id);

    List<ProductRepr> getOrderProducts(Order order);
}
