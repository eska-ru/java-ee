package ru.writeway;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "")
public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("pageHeader", "Главная страница");
        getServletContext().getRequestDispatcher("/elements/page_header").include(req, resp);

        getServletContext().getRequestDispatcher("/elements/page_menu").include(req, resp);

        resp.getWriter().println("<p>Добро пожаловать в наш магазин!</p>");
    }
}
