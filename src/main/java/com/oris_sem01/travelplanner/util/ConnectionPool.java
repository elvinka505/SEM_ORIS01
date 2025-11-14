package com.oris_sem01.travelplanner.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
    private static final String URL = "jdbc:postgresql://localhost:5432/traveldb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "your_password";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
