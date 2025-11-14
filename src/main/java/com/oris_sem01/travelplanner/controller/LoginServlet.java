package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        System.out.println("🔧 Инициализирую LoginServlet...");
        try {
            Connection connection = DatabaseConfig.getConnection();
            UserDaoImpl userDao = new UserDaoImpl(connection);
            userService = new UserServiceImpl(userDao);
            System.out.println("✅ LoginServlet готов!");
        } catch (Exception e) {
            System.err.println("❌ Ошибка при инициализации:");
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Редирект на HTML форму
        response.sendRedirect("/travelplanner/login.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("📧 Попытка входа: " + email);

        if (userService == null) {
            System.err.println("❌ userService = null!");
            response.sendRedirect("/travelplanner/login.html?error=500");
            return;
        }

        try {
            boolean authenticated = userService.authenticate(email, password);

            if (authenticated) {
                System.out.println("✅ Вход успешен!");
                Optional<User> user = userService.getByEmail(email);
                if (user.isPresent()) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user.get());
                    response.sendRedirect("/travelplanner/");
                    return;
                }
            }

            System.err.println("❌ Неверный пароль");
            response.sendRedirect("/travelplanner/login.html?error=invalid");
        } catch (Exception e) {
            System.err.println("❌ Ошибка при входе:");
            e.printStackTrace();
            response.sendRedirect("/travelplanner/login.html?error=500");
        }
    }
}
