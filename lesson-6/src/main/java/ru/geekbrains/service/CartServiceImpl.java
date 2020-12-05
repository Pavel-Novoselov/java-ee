package ru.geekbrains.service;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateful
public class CartServiceImpl implements CartService, Serializable {

    private static final long serialVersionUID = -4911337521943163839L;

    private List<ProductRepr> cartList;


    @Override
    public List<ProductRepr> getAll() {
        if (cartList == null) {
            return Collections.emptyList();
        }
        return cartList;
    }

    @Override
    public void add(ProductRepr productRepr) {
        if (cartList == null) {
            cartList = new ArrayList<>();
        }
        cartList.add(productRepr);
    }

    @Override
    public void deleteProductFromCart(ProductRepr product) {
        cartList.remove(product);
    }
}
