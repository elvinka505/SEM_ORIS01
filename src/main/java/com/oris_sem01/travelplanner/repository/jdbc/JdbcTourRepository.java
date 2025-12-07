package com.oris_sem01.travelplanner.repository.jdbc;

import com.oris_sem01.travelplanner.model.Tour;
import com.oris_sem01.travelplanner.repository.TourRepository;
import com.oris_sem01.travelplanner.util.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC-реализация репозитория туров.
 * Работает через ConnectionPool и использует PreparedStatement.
 */
public class JdbcTourRepository implements TourRepository {

    @Override
    public List<Tour> findAll() {
        String sql = "SELECT id, title, destination, price, duration_days, description, tags " +
                "FROM tours ORDER BY id DESC";

        List<Tour> result = new ArrayList<>();

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении списка туров", e);
        }

        return result;
    }

    @Override
    public Optional<Tour> findById(Long id) {
        String sql = "SELECT id, title, destination, price, duration_days, description, tags " +
                "FROM tours WHERE id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                } else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при поиске тура по id=" + id, e);
        }
    }

    @Override
    public void create(Tour tour) {
        // created_at в таблице заполняется DEFAULT now()
        String sql = "INSERT INTO tours (title, destination, price, duration_days, description, tags) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, tour.getTitle());
            ps.setString(2, tour.getDestination());
            ps.setBigDecimal(3, tour.getPrice());
            ps.setInt(4, tour.getDurationDays());
            ps.setString(5, tour.getDescription());
            ps.setString(6, tour.getTags());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long generatedId = keys.getLong(1);
                    tour.setId(generatedId);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании тура", e);
        }
    }

    @Override
    public void update(Tour tour) {
        String sql = "UPDATE tours SET title = ?, destination = ?, price = ?, duration_days = ?, " +
                "description = ?, tags = ? WHERE id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tour.getTitle());
            ps.setString(2, tour.getDestination());
            ps.setBigDecimal(3, tour.getPrice());
            ps.setInt(4, tour.getDurationDays());
            ps.setString(5, tour.getDescription());
            ps.setString(6, tour.getTags());
            ps.setLong(7, tour.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при обновлении тура id=" + tour.getId(), e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM tours WHERE id = ?";

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении тура id=" + id, e);
        }
    }

    /**
     * Маппинг строки ResultSet в объект Tour.
     */
    private Tour mapRow(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getLong("id"));
        tour.setTitle(rs.getString("title"));
        tour.setDestination(rs.getString("destination"));

        BigDecimal price = rs.getBigDecimal("price");
        tour.setPrice(price != null ? price : BigDecimal.ZERO);

        tour.setDurationDays(rs.getInt("duration_days"));
        tour.setDescription(rs.getString("description"));
        tour.setTags(rs.getString("tags"));

        // created_at есть в БД, но в модель можно не класть, если поля нет
        return tour;
    }
}
