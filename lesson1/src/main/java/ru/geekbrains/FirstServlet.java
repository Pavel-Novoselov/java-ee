package ru.geekbrains;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {
    private transient ServletConfig config;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    @Override
    public void service(final ServletRequest req, final ServletResponse res) throws ServletException, IOException {
        res.getWriter().println("<h2>Hi from ru.geekbrains.FirstServlet!!!</h2>");
    }

    @Override
    public String getServletInfo() {
        return "fisrtServlet";
    }

    @Override
    public void destroy() {

    }
}
