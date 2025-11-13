package com.oris_sem01.travelplanner.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public final class CsrfUtil {
    public static final String CSRF_SESSION_KEY = "csrf_token";

    public static String generateToken(HttpSession session) {
        String token = java.util.UUID.randomUUID().toString();
        session.setAttribute(CSRF_SESSION_KEY, token);
        return token;
    }

    public static boolean isValid(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        if (session == null) return false;
        Object sessionToken = session.getAttribute(CSRF_SESSION_KEY);
        String requestToken = req.getParameter("csrf_token");
        return sessionToken != null && sessionToken.equals(requestToken);
    }
}

