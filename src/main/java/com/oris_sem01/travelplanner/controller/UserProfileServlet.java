package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import com.oris_sem01.travelplanner.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/profile")
public class UserProfileServlet extends HttpServlet {

    private UserServiceImpl userService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            UserServiceImpl.getInstance();
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize UserProfileServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }

        Map<String, Object> root = new HashMap<>();
        root.put("user", user);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("profile.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login");
            return;
        }

        user.setFirstName(req.getParameter("firstname"));
        user.setLastName(req.getParameter("lastname"));

        boolean success = userService.update(user);
        if (success) {
            session.setAttribute("user", user);
            resp.sendRedirect("/profile?success=1");
        } else {
            resp.sendRedirect("/profile?error=1");
        }
    }
}
