package ru.geekbrains.service;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Local
public interface CartService {

    public List<ProductRepr> getAll() ;

    public void add(ProductRepr todo) ;

    public void deleteProductFromCart(ProductRepr product);
}
