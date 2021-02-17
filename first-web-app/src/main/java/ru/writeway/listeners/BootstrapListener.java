package ru.writeway.listeners;

import ru.writeway.persist.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootstrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new TemporaryProductRepository();
        sce.getServletContext().setAttribute("productRepository", productRepository);

        UserRepository userRepository = new TemporaryUserRepository();
        sce.getServletContext().setAttribute("userRepository", userRepository);
    }
}
