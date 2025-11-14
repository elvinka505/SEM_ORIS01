package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.dao.impl.BookingDaoImpl;
import com.oris_sem01.travelplanner.model.Booking;
import com.oris_sem01.travelplanner.service.impl.BookingServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/bookings/*")
public class BookingServlet extends HttpServlet {
    private BookingServiceImpl bookingService;
    private Configuration freemarkerConfig;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DatabaseConfig.getConnection();
            bookingService = new BookingServiceImpl(new BookingDaoImpl(connection));
            freemarkerConfig = FreemarkerConfig.getConfig();
        } catch (Exception e) {
            throw new ServletException("Ошибка инициализации", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            listBookings(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            saveBooking(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void listBookings(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<Booking> bookings = bookingService.getAll();
        Map<String, Object> data = new HashMap<>();
        data.put("bookings", bookings);

        Template template = freemarkerConfig.getTemplate("bookings/list.ftl");
        PrintWriter out = response.getWriter();
        template.process(data, out);
    }

    private void saveBooking(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        Object userObj = session.getAttribute("user");

        if (userObj == null) {
            response.sendRedirect("/travelplanner/login");
            return;
        }

        String tourId = request.getParameter("tourId");

        if (tourId != null && !tourId.isEmpty()) {
            Booking booking = new Booking();
            booking.setTourId(Long.parseLong(tourId));
            booking.setUserId(1L);
            booking.setBookingDate(LocalDate.now());
            booking.setStatus("pending");

            bookingService.save(booking);
            response.sendRedirect("/travelplanner/bookings");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
