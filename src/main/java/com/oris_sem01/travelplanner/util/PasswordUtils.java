package com.oris_sem01.travelplanner.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    // Хешировать пароль
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    // Проверить пароль с хешем
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
