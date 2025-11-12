package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.BookingDaoImpl;
import com.oris_sem01.travelplanner.service.impl.BookingServiceImpl;
import com.oris_sem01.travelplanner.model.Booking;
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
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/bookings")
public class BookingServlet extends HttpServlet {

    private BookingServiceImpl bookingService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            bookingService = new BookingServiceImpl(new BookingDaoImpl(connection));
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize BookingServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var bookings = bookingService.getAll();

        Map<String, Object> root = new HashMap<>();
        root.put("bookings", bookings);

        resp.setContentType("text/html;charset=UTF-8");
        try {
            Template template = freemarkerConfig.getTemplate("bookings-list.ftl");
            PrintWriter writer = resp.getWriter();
            template.process(root, writer);
        } catch (TemplateException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("create".equals(action)) {
            addBooking(req, resp);
        } else if ("delete".equals(action)) {
            deleteBooking(req, resp);
        }
    }

    private void addBooking(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Booking booking = new Booking();
        booking.setUserId(Long.parseLong(req.getParameter("userId")));
        booking.setTourId(Long.parseLong(req.getParameter("tourId")));
        booking.setBookingDate(LocalDate.now());
        booking.setStatus("pending");

        boolean success = bookingService.save(booking);
        resp.sendRedirect("/bookings?success=" + (success ? "1" : "0"));
    }

    private void deleteBooking(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long bookingId = Long.parseLong(req.getParameter("id"));
        boolean success = bookingService.delete(bookingId);
        resp.sendRedirect("/bookings?success=" + (success ? "1" : "0"));
    }
}
