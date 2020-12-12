package ru.geekbrains.controllers;

import ru.geekbrains.persist.*;
import ru.geekbrains.service.ProductRepr;
import ru.geekbrains.service.ProductServiceImpl;
import ru.geekbrains.service.ProductServiceLocal;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    private static final long serialVersionUID = -7617779330325672744L;
    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private ProductServiceLocal productService;

    private ProductRepr product;

    private List<ProductRepr> productList;

    private List<Category> categoryList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.productList = productService.findAll();
        this.categoryList = categoryRepository.findAll();
    }

    public List<ProductRepr> getAllProducts() {
        return productService.findAll();
    }

    public ProductRepr getProduct() {
        return product;
    }

    public void setProduct(ProductRepr product) {
        this.product = product;
    }

    public String editProduct(ProductRepr product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public String deleteProduct(ProductRepr product) {
        productService.delete(product.getId());
        return "/index.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        if (product.getId() == null) {
            productService.insert(product);
        } else {
            productService.update(product);
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public String createProduct() {
        this.product = new ProductRepr();
        return "/product.xhtml?faces-redirect=true";
    }

    public String about() {
        return "/about.xhtml?faces-redirect=true";
    }

    public String goHome() {
        return "/index.xhtml?faces-redirect=true";
    }

    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        return "/index.xhtml?faces-redirect=true";
    }

    public List<ProductRepr> getProductList() {
        return productList;
    }

    public void setProductList(final List<ProductRepr> productList) {
        this.productList = productList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(final List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}