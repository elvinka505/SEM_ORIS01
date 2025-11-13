package com.oris_sem01.travelplanner.util;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PasswordMigration {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            // получаем экземпляр сервиса
            UserService userService = UserServiceImpl.getInstance();

            // получаем всех пользователей
            List<User> users = userService.getAll();

            // выводим email каждого
            users.forEach(u -> System.out.println(u.getEmail()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
