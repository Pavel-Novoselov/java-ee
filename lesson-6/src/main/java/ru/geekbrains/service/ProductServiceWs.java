package ru.geekbrains.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface ProductServiceWs {
    @WebMethod
    void insert(ProductRepr productRepr);

    @WebMethod
    void update(ProductRepr productRepr);

    @WebMethod
    void delete(long id);

    @WebMethod
    ProductRepr findById(long id);

    @WebMethod
    List<ProductRepr> findAll();
}
