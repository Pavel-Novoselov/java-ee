package ru.geekbrains.controllers;

import ru.geekbrains.persist.*;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Named
@SessionScoped
public class OrderController implements Serializable {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CartController cartController;

    private Product product;

    private Order order;

    private List<Product> productList;

    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.productList = productRepository.findAll();
        this.categoryList = categoryRepository.findAll();
        this.order = orderRepository.findAll().get(0);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public String editOrder(List<Product> productList) {
        this.productList = productList;
        return "/order.xhtml?faces-redirect=true";
    }

    public String deleteOrder(Order order) {
        orderRepository.delete(order.getId());
        return "/index.xhtml?faces-redirect=true";
    }

    public String saveOrder() {
        if (order.getId() == null) {
            orderRepository.insert(order);
        } else {
            orderRepository.update(order);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String createOrder(List<Product> productList) {
        this.productList = productList;
        this.order = new Order(productList);
        List<Product> list = productList;
        AtomicLong finalSum = new AtomicLong(0L);
        list.forEach(p -> finalSum.addAndGet(p.getPrice().longValue()));
        BigDecimal sum = BigDecimal.valueOf(finalSum.doubleValue());
        this.order.setCrateDateTime(LocalDateTime.now());
        this.order.setSum(sum);
        this.order.setAddress("no address");
        return "/order.xhtml?faces-redirect=true";
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

}
