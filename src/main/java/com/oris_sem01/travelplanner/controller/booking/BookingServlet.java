package com.oris_sem01.travelplanner.controller.booking;

import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
        if (bookingService == null) {
            throw new ServletException("BookingService не найден в ServletContext");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String tourIdParam = req.getParameter("tourId");
        if (tourIdParam == null || tourIdParam.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/tours");
            return;
        }

        long tourId = Long.parseLong(tourIdParam);

        bookingService.create(user.getId(), tourId);

        resp.sendRedirect(req.getContextPath() + "/profile?booking=ok");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // просто уводим на туры
        resp.sendRedirect(req.getContextPath() + "/tours");
    }
}
