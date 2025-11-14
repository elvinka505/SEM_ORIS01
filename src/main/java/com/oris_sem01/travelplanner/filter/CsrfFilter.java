package com.oris_sem01.travelplanner.filter;

import com.oris_sem01.travelplanner.util.CsrfTokenManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class CsrfFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String method = req.getMethod();

        // Для GET — просто создаём токен, если его нет
        if (method.equalsIgnoreCase("GET")) {
            HttpSession session = req.getSession();
            CsrfTokenManager.getToken(session);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        // Для POST/PUT/DELETE — проверка
        if (method.equalsIgnoreCase("POST")
                || method.equalsIgnoreCase("PUT")
                || method.equalsIgnoreCase("DELETE")) {

            HttpSession session = req.getSession(false);
            if (session == null) {
                req.setAttribute("csrfError", "Session is missing");
                req.getRequestDispatcher("/WEB-INF/templates/errors/csrf.ftl")
                        .forward(servletRequest, servletResponse);
                return;
            }

            String csrfToken = req.getParameter("csrfToken");

            if (!CsrfTokenManager.validate(session, csrfToken)) {
                req.setAttribute("csrfError", "Invalid CSRF token");
                req.getRequestDispatcher("/WEB-INF/templates/errors/csrf.ftl")
                        .forward(servletRequest, servletResponse);
                return;
            }
        }

        // Всё ОК
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
