package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.util.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    // Получите UserService так, как у вас устроена инициализация (ServiceLocator, DI, и т.д.)
    private final UserService userService = UserService.getInstance(); // замените на ваш способ

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Отобразить страницу логина (ftl/jsp)
        req.getRequestDispatcher("/WEB-INF/templates/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (email == null || password == null) {
            resp.sendRedirect(req.getContextPath() + "/login?error=missing");
            return;
        }

        Optional<User> optUser = userService.getByEmail(email);
        if (optUser.isPresent()) {
            User user = optUser.get();
            String storedHash = user.getPasswordHash(); // имя геттера проверьте в вашей модели
            if (PasswordUtils.checkPassword(password, storedHash)) {
                // Успешный вход — создаём/обновляем сессию
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user);
                // по безопасности: установите session.setMaxInactiveInterval(...) при необходимости
                resp.sendRedirect(req.getContextPath() + "/tours"); // PRG
                return;
            }
        }

        // Неверные учётные данные — редирект на форму с ошибкой
        resp.sendRedirect(req.getContextPath() + "/login?error=invalid");
    }
}
