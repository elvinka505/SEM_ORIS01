package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.UserDao;
import com.oris_sem01.travelplanner.dao.impl.UserDaoImpl;
import com.oris_sem01.travelplanner.model.User;
import com.oris_sem01.travelplanner.service.UserService;
import com.oris_sem01.travelplanner.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static volatile UserServiceImpl instance;
    private final UserDao userDao;

    private UserServiceImpl() {
        try {
            Connection connection = DatabaseConfig.getConnection();
            this.userDao = new UserDaoImpl(connection);
        } catch (SQLException e) {
            throw new RuntimeException("DB connection error", e);
        }
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            synchronized (UserServiceImpl.class) {
                if (instance == null) {
                    instance = new UserServiceImpl();
                }
            }
        }
        return instance;
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
        return userDao.save(user);
    }

    @Override
    public boolean update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long id) {
        return userDao.delete(id);
    }
}
