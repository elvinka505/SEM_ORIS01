package com.oris_sem01.travelplanner.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    // Измени имя БД на свое
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/travel_db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty007";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
