package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.util.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance(); // замените на ваш способ

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/templates/register.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fname = req.getParameter("firstName");
        String lname = req.getParameter("lastName");
        String email = req.getParameter("email");
        String rawPassword = req.getParameter("password");

        // Минимальная серверная валидация
        if (email == null || rawPassword == null || rawPassword.length() < 6) {
            resp.sendRedirect(req.getContextPath() + "/register?error=badinput");
            return;
        }

        // Хешируем пароль перед сохранением
        String hashed = PasswordUtils.hashPassword(rawPassword);

        User user = new User();
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setEmail(email);
        user.setPasswordHash(hashed); // убедитесь, что модель содержит такое поле

        try {
            userService.create(user);
            // После регистрации — делаем PRG на страницу логина или сразу логиним пользователя и редиректим
            resp.sendRedirect(req.getContextPath() + "/login?registered=true");
        } catch (Exception e) {
            // логируем и возвращаем ошибку регистрации
            e.printStackTrace();
            resp.sendRedirect(req.getContextPath() + "/register?error=exists");
        }
    }
}
