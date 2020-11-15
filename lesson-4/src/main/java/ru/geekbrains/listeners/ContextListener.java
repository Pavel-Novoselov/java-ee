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
        } catch (SQLException ex) {
            logger.error("", ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Closing JDBC connection");

        ServletContext sc = sce.getServletContext();
        Connection connection = (Connection) sc.getAttribute("jdbcConnection");

        try {
            if (connection != null && !connection.isClosed()){
                connection.close();
            }
        } catch (SQLException ex) {
            logger.error("Can't close JDBC connection", ex);
        }
    }

}
