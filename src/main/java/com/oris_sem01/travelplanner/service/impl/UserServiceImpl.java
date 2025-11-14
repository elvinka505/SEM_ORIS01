package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.util.PasswordUtils;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl {
    private final UserDaoImpl userDao;

    public UserServiceImpl(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public Optional<User> getById(Long id) {
        return userDao.findById(id);
    }

    public Optional<User> getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    public List<User> getAll() {
        return userDao.findAll();
    }

    public boolean save(User user) {
        System.out.println("📝 Начинаем сохранение пользователя: " + user.getEmail());

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            System.err.println("❌ Пароль пустой!");
            return false;
        }

        // Хэшируем пароль перед сохранением
        String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        System.out.println("🔐 Пароль захеширован");

        boolean result = userDao.save(user);
        if (result) {
            System.out.println("✅ Пользователь успешно сохранён!");
        } else {
            System.err.println("❌ Не удалось сохранить пользователя!");
        }
        return result;
    }

    public boolean update(User user) {
        return userDao.update(user);
    }

    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    public boolean authenticate(String email, String password) {
        System.out.println("🔐 Попытка аутентификации: " + email);

        Optional<User> user = userDao.findByEmail(email);
        if (user.isEmpty()) {
            System.err.println("❌ Пользователь не найден: " + email);
            return false;
        }

        boolean verified = PasswordUtils.verifyPassword(password, user.get().getPassword());
        if (verified) {
            System.out.println("✅ Пароль верный!");
        } else {
            System.err.println("❌ Пароль неверный!");
        }
        return verified;
    }
}
