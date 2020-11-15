package ru.geekbrains.controllers;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductRepository productRepository;

    private Product product;

    public List<Product> getAllProducts() throws SQLException {
        return productRepository.findAll();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String editProduct(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public String deleteProduct(Product product) throws SQLException {
        productRepository.delete(product.getId());
        return "/index.xhtml?faces-redirect=true";
    }

    public String saveProduct() throws SQLException {
        if (product.getId() == null) {
            productRepository.insert(product);
        } else {
            productRepository.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String about() {
        return "/about.xhtml?faces-redirect=true";
    }

    public String goHome() {
        return "/index.xhtml?faces-redirect=true";
    }
}