package ru.geekbrains.controllers;

import com.sun.org.apache.xpath.internal.operations.Or;
import ru.geekbrains.persist.*;
import ru.geekbrains.service.OrderService;
import ru.geekbrains.service.ProductRepr;

import javax.ejb.EJB;
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

    private static final long serialVersionUID = -5109139938512805288L;
    @EJB
    private OrderService orderService;

    private Order order;

    private List<ProductRepr> productList;

    public String showAllOrders() {
        return "/orders.xhtml?faces-redirect=true";
    }

    public List<Order> getAllOrders(){
        return orderService.findAll();
    }

    public String editOrder(Order order) {
        this.order = order;
        this.productList = orderService.getOrderProducts(order);
        return "/order_edit.xhtml?faces-redirect=true";
    }

    public String deleteOrder(Order order) {
        orderService.delete(order.getId());
        return "/orders.xhtml?faces-redirect=true";
    }

    public String saveOrder() {
        if (order.getId() == null) {
            orderService.insert(order);
        } else {
            orderService.update(order);
        }
        return "/orders.xhtml?faces-redirect=true";
    }

    public String createOrder(List<ProductRepr> productList) {
        this.productList = productList;
        this.order = new Order(productList);
        List<ProductRepr> list = productList;
        AtomicLong finalSum = new AtomicLong(0L);
        list.forEach(p -> finalSum.addAndGet(p.getPrice().longValue()));
        BigDecimal sum = BigDecimal.valueOf(finalSum.doubleValue());
        this.order.setCrateDateTime(LocalDateTime.now());
        this.order.setSum(sum);
        this.order.setAddress("no address");
        return "/order.xhtml?faces-redirect=true";
    }

    public List<ProductRepr> getProductList() {
        return productList;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

}
