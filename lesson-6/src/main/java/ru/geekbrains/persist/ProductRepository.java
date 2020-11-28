package ru.geekbrains.persist;

import ru.geekbrains.service.ProductRepr;

import javax.ejb.Local;
import javax.ejb.TransactionAttribute;
import javax.transaction.Transactional;
import java.util.List;

@Local
public interface ProductRepository {
    void insert(Product product);

   void update(Product product);

    void delete(long id);

    Product findById(long id);

    List<Product> findAll();

    ProductRepr findProductReprById(long id);

    List<ProductRepr> findAllProductRepr();
}
