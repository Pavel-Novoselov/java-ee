package ru.geekbrains.controllers;

import ru.geekbrains.persist.Category;
import ru.geekbrains.persist.CategoryRepository;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
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

    @Inject
    private CategoryRepository categoryRepository;

    private Product product;

    private Category category;

    private List<Product> productList;

    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.productList = productRepository.findAll();
        this.categoryList = categoryRepository.findAll();
    }

    public List<Product> getAllProducts() {
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
        this.category = product.getCategory();
        return "/product.xhtml?faces-redirect=true";
    }

    public String deleteProduct(Product product) {
        productRepository.delete(product.getId());
        return "/index.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        if (product.getId() == null) {
            categoryRepository.insert(product.getCategory());
            productRepository.insert(product);
        } else {
            productRepository.update(product);
            categoryRepository.update(product.getCategory());
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.category = new Category("no name");
        this.product = new Product();
        this.product.setCategory(this.category);
        return "/product.xhtml?faces-redirect=true";
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String about() {
        return "/about.xhtml?faces-redirect=true";
    }

    public String goHome() {
        return "/index.xhtml?faces-redirect=true";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }
}