package ru.geekbrains.service;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.Future;

@Stateless
public class ProductServiceImpl implements ProductServiceLocal {
    @Inject
    private ProductRepository productRepository;

    @Inject
    private CategoryRepository categoryRepository;

    @TransactionAttribute
    @Override
    public void insert(ProductRepr productRepr) {
        Category category = categoryRepository.findById(productRepr.getCategoryId());
        productRepository.insert(new Product(null, productRepr.getDescription(), productRepr.getPrice(), category));
    }

    @TransactionAttribute
    @Override
    public void update(ProductRepr productRepr) {
        Category category = categoryRepository.findById(productRepr.getCategoryId());
        productRepository.update(new Product(productRepr.getId(), productRepr.getDescription(), productRepr.getPrice(), category));
    }

    @TransactionAttribute
    @Override
    public void delete(long id) {
        productRepository.delete(id);
    }

    @Override
    public ProductRepr findById(long id) {
        return productRepository.findProductReprById(id);
    }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAllProductRepr();
    }

    @Asynchronous
    @Override
    public Future<ProductRepr> findByIdAsync(long id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult(productRepository.findById(id));
    }
}