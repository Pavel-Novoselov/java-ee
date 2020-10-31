package ru.geekbrains;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/header_page").include(req, resp);

        resp.getWriter().println("<h1>Main page</h1>");

        getServletContext().getRequestDispatcher("/footer_page").include(req, resp);
    }
}
