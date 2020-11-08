package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/header_page")
public class HeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<html>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("<link rel='stylesheet' href = '" + req.getContextPath() + "/Style.css'>");
        resp.getWriter().println("<ul>");
        resp.getWriter().println("<li><a href = " + req.getContextPath() + "/main" + ">Main page</a></li>");
        resp.getWriter().println("<li><a href = " + req.getContextPath() + "/catalog" + ">Catalog</a></li>");
        resp.getWriter().println("<li><a href = " + req.getContextPath() + "/product" + ">Product</a></li>");
        resp.getWriter().println("<li><a href = " + req.getContextPath() + "/cart" + ">Cart</a></li>");
        resp.getWriter().println("<li><a href = " + req.getContextPath() + "/order" + ">Order</a></li>");
        resp.getWriter().println("</ul>");

    }
}
