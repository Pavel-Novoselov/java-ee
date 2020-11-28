package ru.geekbrains.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import ru.geekbrains.persist.Order;

import javax.ejb.Local;
import java.util.List;
import java.util.concurrent.Future;

@Local
public interface OrderService {
    void insert(Order order);

    void update(Order order);

    void delete(long id);

    Order findById(long id);

    List<Order> findAll();

    List<ProductRepr> getOrderProducts(Order order);

}
