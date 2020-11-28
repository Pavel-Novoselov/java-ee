package ru.geekbrains.service;

import javax.ejb.Remote;
import java.util.List;
import java.util.concurrent.Future;

@Remote
public interface ProductServiceRemote {
    List<ProductRepr> findAll();

    Future<ProductRepr> findByIdAsync(long id);
}
