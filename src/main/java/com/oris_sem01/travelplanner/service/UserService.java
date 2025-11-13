package com.oris_sem01.travelplanner.service;

import com.oris_sem01.travelplanner.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getById(Long id);
    Optional<User> getByEmail(String email);
    List<User> getAll();
    boolean save(User user);
    boolean update(User user);
    boolean delete(Long id);
}
