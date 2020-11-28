package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;
import ru.geekbrains.persist.ProductRepositoryImpl;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.CartServiceImpl;
import ru.geekbrains.service.ProductRepr;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class CartController implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);
    private static final long serialVersionUID = -8149425990630833538L;

    @EJB
    private CartService cartService;

    private ProductRepr product;

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(final ProductRepr product) {
        this.product = product;
    }

    public List<ProductRepr> getCartList() {
        return cartService.getAll();
    }

    public String addProductToCart(ProductRepr product) {
        cartService.add(product);
        return "/cart.xhtml?faces-redirect=true";
    }

    public String deleteProductFromCart(ProductRepr product) {
        cartService.deleteProductFromCart(product);
        return "/cart.xhtml?faces-redirect=true";
    }

    public String showCart() {
        return "/cart.xhtml?faces-redirect=true";
    }

    public String makeOrder() {
        return "/order.xhtml?faces-redirect=true";
    }

}
