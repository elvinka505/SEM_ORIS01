package com.oris_sem01.travelplanner.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    public static String hash(String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt(12));
    }

    public static boolean check(String plain, String hashed) {
        if (hashed == null || hashed.isBlank()) {
            return false;
        }
        return BCrypt.checkpw(plain, hashed);
    }
}
