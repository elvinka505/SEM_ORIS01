package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.config.FreemarkerConfig;
import com.oris_sem01.travelplanner.model.Booking;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.BookingService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    private Configuration freemarkerConfig;
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        freemarkerConfig = FreemarkerConfig.getConfig(getServletContext());
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");

        List<Booking> bookings = bookingService != null
                ? bookingService.findByUserId(user.getId())
                : List.of();

        Map<String, Object> model = new HashMap<>();
        model.put("user", user);
        model.put("bookings", bookings);
        model.put("request", req);

        if ("ok".equals(req.getParameter("booking"))) {
            model.put("success", "Тур успешно забронирован! ✨");
        }

        Template template = freemarkerConfig.getTemplate("profile.ftl");
        resp.setContentType("text/html;charset=UTF-8");
        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            throw new ServletException("Ошибка обработки шаблона profile.ftl", e);
        }
    }
}
