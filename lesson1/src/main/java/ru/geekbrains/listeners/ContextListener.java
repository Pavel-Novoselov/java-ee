package ru.geekbrains.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class ContextListener implements ServletContextListener {

    public static final String DB_CONNECTION = "dbConnection";
    public static final String PRODUCT_REPO = "ProductRepo";
    public static final String CART_LIST = "CartList";

    private static final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing application");

        ServletContext sc = sce.getServletContext();
        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, username, password);
            sc.setAttribute(DB_CONNECTION, conn);

            ProductRepository productRepository = new ProductRepository(conn);
            sc.setAttribute(PRODUCT_REPO, productRepository);

            List<Product> cartList = new ArrayList<>();
            sc.setAttribute(CART_LIST, cartList);

            if (productRepository.findAll().isEmpty()) {
                productRepository.insert(new Product(1L, "First", BigDecimal.valueOf(100.00)));
                productRepository.insert(new Product(2L, "Second", BigDecimal.valueOf(200.00)));
                productRepository.insert(new Product(3L, "Third", BigDecimal.valueOf(300.00)));
            }
        } catch (SQLException ex) {
            logger.error("", ex);
        }

    }
}
