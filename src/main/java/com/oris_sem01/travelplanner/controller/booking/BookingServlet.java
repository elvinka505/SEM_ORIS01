package com.oris_sem01.travelplanner.controller.booking;

import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.BookingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    private BookingService bookingService;

    @Override
    public void init() {
        bookingService = (BookingService)
                getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String tourIdParam = req.getParameter("tourId");
        if (tourIdParam == null || tourIdParam.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/tours");
            return;
        }

        Long tourId = Long.valueOf(tourIdParam);
        User user = (User) session.getAttribute("user");

        bookingService.create(user.getId(), tourId);

        resp.sendRedirect(req.getContextPath() + "/profile?booking=ok");
    }
}
