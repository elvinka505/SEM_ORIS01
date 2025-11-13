package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserServiceImpl userService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            UserServiceImpl.getInstance();
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize UserServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("users", userService.getAll());
        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("users/list.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }
}
