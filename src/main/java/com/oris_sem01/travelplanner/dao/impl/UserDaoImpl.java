package com.oris_sem01.travelplanner.dao.impl;

import com.oris_sem01.travelplanner.dao.UserDao;
import com.oris_sem01.travelplanner.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<User> findById(Long id) {
        String sql = "SELECT id, email, password_hash, first_name, last_name, role FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToUser(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске пользователя по ID: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT id, email, password_hash, first_name, last_name, role FROM users WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToUser(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при поиске пользователя по email: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, email, password_hash, first_name, last_name, role FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(mapRowToUser(rs));
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при получении всех пользователей: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO users (email, password_hash, first_name, last_name, role) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getRole() != null ? user.getRole() : "user");

            int result = ps.executeUpdate();
            if (result > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        user.setId(keys.getLong(1));
                    }
                }
            }
            System.out.println("✅ Пользователь сохранён: " + user.getEmail() + " (результат: " + result + ")");
            return result > 0;
        } catch (SQLException e) {
            System.err.println("❌ Ошибка при сохранении пользователя: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE users SET email = ?, password_hash = ?, first_name = ?, last_name = ?, role = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getRole());
            ps.setLong(6, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении пользователя: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private User mapRowToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password_hash"));  // ✅ password_hash
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setRole(rs.getString("role"));
        return user;
    }
}
