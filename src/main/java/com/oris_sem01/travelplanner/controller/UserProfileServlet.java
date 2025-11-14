package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;  // ← ДОБАВИТЬ этот импорт
import java.util.Optional;

@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {

    private final UserService userService = (UserService) getServletContext().getAttribute("userService");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // ✅ getById() возвращает Optional<User>
        Optional<User> userOpt = userService.getById(userId);

        if (userOpt.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        req.setAttribute("user", userOpt.get());
        req.getRequestDispatcher("/WEB-INF/templates/users/profile.ftl").forward(req, resp);
    }
}
