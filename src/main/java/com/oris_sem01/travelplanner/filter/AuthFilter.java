package com.oris_sem01.travelplanner.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class AuthFilter implements Filter {

    private Set<String> publicPaths = Set.of(
            "/login", "/register", "/css/", "/js/", "/images/", "/api/public"
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // можно загрузить публичные пути из context-param при необходимости
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // Разрешаем все статические ресурсы и публичные пути
        boolean allowed = publicPaths.stream().anyMatch(path::startsWith);

        if (allowed) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // Неавторизованный — редирект на страницу логина
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // авторизован — пропускаем запрос
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // очистка при завершении фильтра
    }
}
