package com.oris_sem01.travelplanner.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebFilter("/*")
public class LoggingFilter implements Filter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Можно добавить инициализацию, если нужно
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String method = httpRequest.getMethod();
        String path = httpRequest.getRequestURI();
        String timestamp = LocalDateTime.now().format(formatter);
        long startTime = System.currentTimeMillis();

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.err.println("[" + timestamp + "] ERROR: " + method + " " + path);
            e.printStackTrace();
            httpResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("[" + timestamp + "] " + method + " " + path + " - " + httpResponse.getStatus() + " (" + duration + "ms)");
    }

    @Override
    public void destroy() {
        // Cleanup, если нужно
    }
}
