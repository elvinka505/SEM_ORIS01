package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.TourDaoImpl;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.dao.impl.BookingDaoImpl;
import com.oris_sem01.travelplanner.service.impl.TourServiceImpl;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import com.oris_sem01.travelplanner.service.impl.BookingServiceImpl;
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

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private TourServiceImpl tourService;
    private UserServiceImpl userService;
    private BookingServiceImpl bookingService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            tourService = new TourServiceImpl(new TourDaoImpl(connection));
            UserServiceImpl.getInstance();
            bookingService = new BookingServiceImpl(new BookingDaoImpl(connection));
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize AdminServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("tours", tourService.getAll());
        root.put("users", userService.getAll());
        root.put("bookings", bookingService.getAll());

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("admin-dashboard.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }
}
