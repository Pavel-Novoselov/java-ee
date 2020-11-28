package ru.geekbrains.service;

import ru.geekbrains.persist.Order;
import ru.geekbrains.persist.OrderRepository;
import ru.geekbrains.persist.Product;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Stateless
public class OrderServiceImpl implements OrderService {

    @EJB
    private OrderRepository orderRepository;

    @Override
    public void insert(Order order) {
        orderRepository.insert(order);
    }

    @Override
    public void update(Order order) {
        orderRepository.update(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.delete(id);
    }

    @Override
    public Order findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<ProductRepr> getOrderProducts(Order order) {
        return orderRepository.getOrderProducts(order);
    }
}
