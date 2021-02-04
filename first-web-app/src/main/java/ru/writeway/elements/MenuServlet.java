package ru.writeway.elements;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/elements/page_menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("<p>У нас есть несколько разделов:</p>");
        resp.getWriter().println(createMenuItem("/", "Главная", req));
        resp.getWriter().println(createMenuItem("/catalog", "Каталог", req));
        resp.getWriter().println(createMenuItem("/product", "Товар", req));
        resp.getWriter().println(createMenuItem("/cart", "Корзина", req));
        resp.getWriter().println(createMenuItem("/order", "Заказ", req));
    }

    private String createMenuItem(String path, String name, HttpServletRequest req) {
        String res = "<p><a href='" + req.getContextPath() + path + "'>" + name + "</a>";

        String reqPath = req.getServletPath();
        // Сравнение не корректное, т.к. не учитывает возможности использования каталогов,
        // но этим в данном случае пренебрегаем
        if (reqPath.equals(path)) {
            res += " <-- Вы уже здесь!";
        }

        res += "</p>";
        return res;
    }
}
