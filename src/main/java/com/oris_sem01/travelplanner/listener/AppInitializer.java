package com.oris_sem01.travelplanner.listener;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("=== Application Started ===");
        System.out.println("Travel Planner Web Application initialized");

        try {
            Connection conn = DatabaseConfig.getConnection();
            if (conn != null) {
                System.out.println("Database connection successful");
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("=== Application Stopped ===");
        System.out.println("Travel Planner Web Application destroyed");
    }
}
