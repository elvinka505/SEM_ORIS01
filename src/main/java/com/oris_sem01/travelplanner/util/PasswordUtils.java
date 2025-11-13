package com.oris_sem01.travelplanner.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtils {

    private static final int LOG_ROUNDS = 12; // можете уменьшить при медленной сборке

    private PasswordUtils() {}

    public static String hashPassword(String plainTextPassword) {
        if (plainTextPassword == null) throw new IllegalArgumentException("Password is null");
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(LOG_ROUNDS));
    }

    public static boolean checkPassword(String plainPassword, String storedHash) {
        if (plainPassword == null || storedHash == null) return false;
        try {
            return BCrypt.checkpw(plainPassword, storedHash);
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
