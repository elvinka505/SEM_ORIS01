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
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private UserServiceImpl userService;

    @Override
    public void init() throws ServletException {
        System.out.println("🔧 Инициализирую RegisterServlet...");
        try {
            Connection connection = DatabaseConfig.getConnection();
            UserDaoImpl userDao = new UserDaoImpl(connection);
            userService = new UserServiceImpl(userDao);
            System.out.println("✅ RegisterServlet готов!");
        } catch (Exception e) {
            System.err.println("❌ Ошибка при инициализации:");
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        System.out.println("📨 Регистрация: " + email);

        if (userService == null) {
            System.err.println("❌ userService = null!");
            response.sendRedirect("/travelplanner/register.html?error=500");
            return;
        }

        try {
            if (email == null || email.isEmpty() || password == null || password.length() < 6) {
                System.err.println("❌ Некорректные данные");
                response.sendRedirect("/travelplanner/register.html?error=invalid");
                return;
            }

            if (userService.getByEmail(email).isPresent()) {
                System.err.println("❌ Email уже зарегистрирован");
                response.sendRedirect("/travelplanner/register.html?error=exists");
                return;
            }

            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName != null ? firstName : "");
            user.setLastName(lastName != null ? lastName : "");
            user.setRole("user");

            if (userService.save(user)) {
                System.out.println("✅ Пользователь зарегистрирован!");
                response.sendRedirect("/travelplanner/login.html?success=true");
                return;
            } else {
                System.err.println("❌ Ошибка сохранения в БД");
                response.sendRedirect("/travelplanner/register.html?error=save");
            }
        } catch (Exception e) {
            System.err.println("❌ Ошибка при регистрации:");
            e.printStackTrace();
            response.sendRedirect("/travelplanner/register.html?error=500");
        }
    }
}
