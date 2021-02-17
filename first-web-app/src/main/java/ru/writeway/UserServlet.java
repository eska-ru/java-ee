package ru.writeway;

import ru.writeway.persist.Product;
import ru.writeway.persist.ProductRepository;
import ru.writeway.persist.User;
import ru.writeway.persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
        if (userRepository == null) {
            throw new ServletException("UserRepository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getPathInfo() == null || req.getPathInfo().equals("/")) {
            req.setAttribute("users", userRepository.findAll());
            getServletContext().getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/edit")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            User user = userRepository.findById(id);
            if (user == null) {
                resp.setStatus(404);
                return;
            }
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        } else if (req.getPathInfo().equals("/delete")) {
            long id;
            try {
                id = Long.parseLong(req.getParameter("id"));
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
            if (userRepository.deleteById(id)) {
                resp.sendRedirect(getServletContext().getContextPath() + "/user");
            } else {
                resp.setStatus(404);
            }
        } else if (req.getPathInfo().equals("/add")) {
            User user = new User(null, "", "", "", "");
            req.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/user_form.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id;

        String str = req.getParameter("id");
        if (str.isEmpty()) {
            id = null;
        } else {
            try {
                id = Long.parseLong(str);
            } catch (NumberFormatException ex) {
                resp.setStatus(400);
                return;
            }
        }

        User user = new User(id, req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("email"), req.getParameter("phone"));
        userRepository.saveOrUpdate(user);
        resp.sendRedirect(getServletContext().getContextPath() + "/user");
    }
}
