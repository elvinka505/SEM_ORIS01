package com.oris_sem01.travelplanner.controller;

import com.oris_sem01.travelplanner.util.CsrfTokenManager;  // ← ИМПОРТ
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        // ✅ Проверяем, что пользователь админ
        // (твоя логика проверки прав)

        String csrfToken = CsrfTokenManager.generateToken(session);  // ✅ Исправлено
        req.setAttribute("csrfToken", csrfToken);

        req.getRequestDispatcher("/WEB-INF/templates/admin/dashboard.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String token = req.getParameter("csrfToken");

        // ✅ Проверяем CSRF токен
        if (!CsrfTokenManager.isValid(session, token)) {  // ✅ Исправлено
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
            return;
        }

        // Остальная логика админа...
    }
}
