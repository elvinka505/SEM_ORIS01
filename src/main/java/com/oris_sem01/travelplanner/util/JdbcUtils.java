package com.oris_sem01.travelplanner.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
    private static final String URL = "jdbc:postgresql://localhost:5432/travel_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwerty007"; // ваш пароль

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
