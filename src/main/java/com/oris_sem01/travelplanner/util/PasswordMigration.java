package com.oris_sem01.travelplanner.util;

import com.oris_sem01.travelplanner.service.impl.UserServiceImpl;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.util.ConnectionPool;

public class PasswordMigration {

    public static void migratePasswords() {
        try {
            // ✅ Создаём Connection и передаём в DAO
            var connection = ConnectionPool.getConnection();
            UserDaoImpl userDao = new UserDaoImpl(connection);

            // ✅ Теперь передаём UserDao в getInstance()
            UserServiceImpl userService = new UserServiceImpl(new UserDaoImpl(connection));

            System.out.println("✓ Password migration started");
            // Логика миграции здесь...
        } catch (Exception e) {
            System.err.println("✗ Ошибка миграции: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        migratePasswords();
    }
}
