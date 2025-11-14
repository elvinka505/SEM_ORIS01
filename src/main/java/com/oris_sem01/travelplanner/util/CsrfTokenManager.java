package com.oris_sem01.travelplanner.util;

import jakarta.servlet.http.HttpSession;
import java.util.UUID;

public class CsrfTokenManager {

    private static final String CSRF_TOKEN_ATTR = "csrfToken";

    public static String generateToken(HttpSession session) {
        String token = UUID.randomUUID().toString();
        session.setAttribute(CSRF_TOKEN_ATTR, token);
        return token;
    }

    public static boolean isValid(HttpSession session, String token) {
        if (session == null || token == null) {
            return false;
        }
        String sessionToken = (String) session.getAttribute(CSRF_TOKEN_ATTR);
        return token.equals(sessionToken);
    }

    public static String getToken(HttpSession session) {
        return (String) session.getAttribute(CSRF_TOKEN_ATTR);
    }
}
