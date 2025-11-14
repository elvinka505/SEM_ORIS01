package com.oris_sem01.travelplanner.util;

import jakarta.servlet.http.HttpSession;

import java.security.SecureRandom;
import java.util.Base64;

public class CsrfTokenManager {

    private static final String CSRF_SESSION_ATTRIBUTE = "csrfToken";
    private static final SecureRandom secureRandom = new SecureRandom();

    /** 🔥 Новый метод (совместим с твоим проектом) */
    public static String getToken(HttpSession session) {
        String token = (String) session.getAttribute(CSRF_SESSION_ATTRIBUTE);
        if (token == null) {
            token = generateToken(session);
        }
        return token;
    }

    /** 🔥 Новый метод (совместим с твоим проектом) */
    public static boolean validate(HttpSession session, String token) {
        return isValid(session, token);
    }

    /** Основной метод генерации */
    public static String generateToken(HttpSession session) {
        String token = createSecureToken();
        session.setAttribute(CSRF_SESSION_ATTRIBUTE, token);
        return token;
    }

    /** Основная проверка токена */
    public static boolean isValid(HttpSession session, String tokenFromForm) {
        if (tokenFromForm == null) return false;

        String expectedToken = (String) session.getAttribute(CSRF_SESSION_ATTRIBUTE);
        return expectedToken != null && expectedToken.equals(tokenFromForm);
    }

    /** Генерация случайного безопасного токена */
    private static String createSecureToken() {
        byte[] bytes = new byte[32];
        secureRandom.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
