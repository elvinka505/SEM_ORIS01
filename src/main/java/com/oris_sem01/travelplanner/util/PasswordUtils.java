package com.oris_sem01.travelplanner.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Метод для хэширования пароля
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Метод для проверки пароля
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
