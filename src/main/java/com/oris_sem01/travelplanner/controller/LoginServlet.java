package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import com.oris_sem01.travelplanner.util.PasswordUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = UserServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/templates/login.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        Optional<User> userOptional = userService.getByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            boolean passwordValid = PasswordUtils.verifyPassword(password, user.getPasswordHash());

            if (passwordValid) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/index");
                return;
            }
        }

        req.setAttribute("error", "Неверный email или пароль");
        req.getRequestDispatcher("/WEB-INF/templates/login.ftl").forward(req, resp);
    }
}
