package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import com.oris_sem01.travelplanner.util.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/templates/register.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        if (email == null || password == null || firstName == null || lastName == null ||
                email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            req.setAttribute("error", "Все поля обязательны для заполнения");
            req.getRequestDispatcher("/WEB-INF/templates/register.ftl").forward(req, resp);
            return;
        }

        String passwordHash = PasswordUtils.hashPassword(password);
        User newUser = new User(null, email, passwordHash, firstName, lastName, false);

        boolean success = userService.save(newUser);

        if (success) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            req.setAttribute("error", "Ошибка регистрации, возможно email уже используется");
            req.getRequestDispatcher("/WEB-INF/templates/register.ftl").forward(req, resp);
        }
    }
}
