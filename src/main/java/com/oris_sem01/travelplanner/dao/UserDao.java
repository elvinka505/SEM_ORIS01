package com.oris_sem01.travelplanner.dao;

import com.oris_sem01.travelplanner.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    List<User> findAll();
    boolean update(User user);
    boolean delete(Long id);
}
