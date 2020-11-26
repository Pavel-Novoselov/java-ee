package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Inject
    private ProductRepository repository;

    private Product product;
    private List<Product> cartList;

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }

    public List<Product> getCartList() {
        if (cartList == null) {
            cartList = new ArrayList<>();

        }
        return cartList;
    }

    public String addProductToCart(Product product) {
        if (cartList == null) {
            cartList = new ArrayList<>();
        }
        cartList.add(product);
        return "/cart.xhtml?faces-redirect=true";
    }

    public String deleteProductFromCart(Product product) {
        cartList.remove(product);
        return "/cart.xhtml?faces-redirect=true";
    }

    public String showCart() {
        return "/cart.xhtml?faces-redirect=true";
    }

    public String makeOrder() {
        return "/order.xhtml?faces-redirect=true";
    }



}
