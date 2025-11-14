package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.UserDao;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.util.PasswordUtils;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // удобный фабричный метод (используется в сервлетах)
    public static UserServiceImpl getInstance(UserDaoImpl dao) {
        return new UserServiceImpl(dao);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public boolean save(User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            System.err.println("Password empty");
            return false;
        }
        String hashed = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashed);
        return userDao.save(user);
    }

    @Override
    public boolean update(User user) {
        // если пароль не пустой — хэшируем его перед обновлением
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            String hashed = PasswordUtils.hashPassword(user.getPassword());
            user.setPassword(hashed);
        }
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }

    // Вспомогательный метод аутентификации (не часть интерфейса)
    public boolean authenticate(String email, String password) {
        Optional<User> userOpt = userDao.findByEmail(email);
        if (userOpt.isEmpty()) return false;
        String storedHash = userOpt.get().getPassword();
        return PasswordUtils.verifyPassword(password, storedHash);
    }
}
