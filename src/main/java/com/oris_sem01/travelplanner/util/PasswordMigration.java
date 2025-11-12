package com.oris_sem01.travelplanner.util;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import com.oris_sem01.travelplanner.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public class PasswordMigration {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl(connection));
            var users = userService.getAll();
            users.forEach(u -> System.out.println(u.getEmail()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
