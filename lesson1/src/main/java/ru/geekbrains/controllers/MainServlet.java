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

import static ru.geekbrains.listeners.ContextListener.PRODUCT_REPO;

@WebServlet(name = "ProductServlet", urlPatterns = {"", "/"})
public class MainServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);

    private ProductRepository repository;

    @Override
    public void init() throws ServletException {
        this.repository = (ProductRepository) getServletContext().getAttribute(PRODUCT_REPO);

        if (this.repository == null) {
            throw new ServletException("ProductRepository is not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("PathInfo: {}", req.getPathInfo());
        logger.info("ServletPath: {}", req.getServletPath());
        logger.info("ResourceURL: {}", getServletContext().getResource("/WEB-INF/templates/index.jsp"));

        if (req.getServletPath().equals("/")) {
            showAll(req, resp);
        } else if (req.getServletPath().equals("/new")) {
            showNewProductPage(req, resp);
        } else if (req.getServletPath().equals("/edit")) {
            showEditProductPage(req, resp);
        } else if (req.getServletPath().equals("/delete")) {
            deleteProduct(req, resp);
        } else if (req.getServletPath().equals("/showOne")) {
            showOneProduct(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Price {}", req.getParameter("price"));

        if (req.getServletPath().equals("/update")) {
            updateProduct(req, resp);
        } else if (req.getServletPath().equals("/create")) {
            createProduct(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            req.setAttribute("products", repository.findAll());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/templates/index.jsp").forward(req, resp);
    }

    private void showOneProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        try {
            req.setAttribute("product", repository.findById(id));
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/templates/oneProduct.jsp").forward(req, resp);
    }

    private void showNewProductPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("product", new Product());
        req.setAttribute("action", "create");
        getServletContext().getRequestDispatcher("/WEB-INF/templates/product.jsp").forward(req, resp);
    }

    private void showEditProductPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
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
        req.setAttribute("product", product);
        req.setAttribute("action", "update");
        getServletContext().getRequestDispatcher("/WEB-INF/templates/product.jsp").forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id;
        try {
            id = Long.parseLong(req.getParameter("id"));
        } catch (Exception ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
            return;
        }
        try {
            repository.delete(id);
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            repository.update(new Product(
                    Long.parseLong(req.getParameter("id")),
                    req.getParameter("description"),
                    BigDecimal.valueOf(Long.parseLong(req.getParameter("price")))));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            repository.insert(new Product(
                    -1L,
                    req.getParameter("description"),
                    BigDecimal.valueOf(Long.parseLong(req.getParameter("price")))));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (NumberFormatException ex) {
            logger.error("", ex);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
