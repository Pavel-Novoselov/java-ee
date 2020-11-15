package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.geekbrains.listeners.ContextListener.CART_LIST;
import static ru.geekbrains.listeners.ContextListener.PRODUCT_REPO;

@WebServlet(urlPatterns = "/cart/*")
public class Cart extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);
    private ProductRepository repository;
    private List<Product> cartList;

    @Override
    public void init() throws ServletException {
        this.repository = (ProductRepository) getServletContext().getAttribute(PRODUCT_REPO);
        if (this.repository == null) {
            throw new ServletException("ProductRepository is not initialized");
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cartList = (List<Product>) getServletContext().getAttribute(CART_LIST);
        if (req.getServletPath().equals("/cart")) {
            showCart(req, resp);
        } else if (req.getServletPath().equals("/cart/add")) {
            addProductToCart(req, resp);
        } else if (req.getServletPath().equals("/cart/delete")) {
            deleteProductFromCart(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        getServletContext().getRequestDispatcher("/WEB-INF/templates/cart.jsp").forward(req, resp);
    }

    private void addProductToCart(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        if (cartList == null){
            cartList = new ArrayList<>();
        }
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            return;
        }
        Product product;
        try {
            product = repository.findById(id);
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        cartList.add(product);
        req.setAttribute("products", cartList);
        getServletContext().setAttribute(CART_LIST, cartList);
        showCart(req, resp);
    }

    private void deleteProductFromCart(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            return;
        }
        List<Product> list = new ArrayList<>();
        for (Product product : cartList) {
            if (product.getId() != id) {
                list.add(product);
            }
        }
        cartList = list;
        getServletContext().getRequestDispatcher("/WEB-INF/templates/cart.jsp").forward(req, resp);
    }
}
