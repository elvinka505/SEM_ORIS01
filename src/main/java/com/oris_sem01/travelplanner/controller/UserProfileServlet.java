package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {

    private UserService userService; // инициализируем в init()

    @Override
    public void init() throws ServletException {
        // пытаемся получить из контекста (если инициализируется WebListener)
        Object svc = getServletContext().getAttribute("userService");
        if (svc instanceof UserService) {
            this.userService = (UserService) svc;
            return;
        }

        // fallback — создаём локально (нужен DatabaseConfig)
        try {
            Connection conn = DatabaseConfig.getConnection();
            this.userService = UserServiceImpl.getInstance(new UserDaoImpl(conn));
        } catch (SQLException e) {
            throw new ServletException("Не удалось инициализировать UserService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        Optional<User> userOpt = userService.getById(userId);

        if (userOpt.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // для CSRF: токен должен быть сгенерирован и лежать в сессии/аттрибутах ранее
        // req.setAttribute("csrfToken", session.getAttribute("csrfToken"));

        req.setAttribute("user", userOpt.get());
        req.getRequestDispatcher("/WEB-INF/templates/users/profile.ftl").forward(req, resp);
    }
}
