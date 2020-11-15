package ru.geekbrains.controllers;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class OrderController implements Serializable {
    @Inject
    private ProductRepository productRepository;

    private Product product;


    public String saveOrder() {
        return "/index.xhtml?faces-redirect=true";
    }
}
