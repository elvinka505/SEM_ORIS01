package com.oris_sem01.travelplanner.listener;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import com.oris_sem01.travelplanner.util.ConnectionPool;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        try {
            // ✅ UserDaoImpl требует Connection
            // Получаем соединение из пула (или создаём одно)
            var connection = ConnectionPool.getConnection();

            UserDaoImpl userDao = new UserDaoImpl(connection);
            UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl(connection));

            context.setAttribute("userService", userService);

            System.out.println("✓ AppInitializer: UserService инициализирован");
        } catch (Exception e) {
            System.err.println("✗ Ошибка инициализации: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("✓ Приложение остановлено");
    }
}
