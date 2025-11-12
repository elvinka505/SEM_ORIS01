package com.oris_sem01.travelplanner.dao.impl;

import com.oris_sem01.travelplanner.dao.TourDao;
import com.oris_sem01.travelplanner.model.Tour;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDaoImpl implements TourDao {

    private final Connection connection;

    public TourDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Tour> findById(Long id) {
        String sql = "SELECT id, name, description, price FROM tours WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToTour(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        String sql = "SELECT id, name, description, price FROM tours";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tours.add(mapRowToTour(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public List<Tour> findAll(int offset, int limit) {
        List<Tour> tours = new ArrayList<>();
        String sql = "SELECT id, name, description, price FROM tours ORDER BY id LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tours.add(mapRowToTour(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public List<Tour> searchByName(String name, int offset, int limit) {
        List<Tour> tours = new ArrayList<>();
        String sql = "SELECT id, name, description, price FROM tours WHERE name ILIKE ? ORDER BY id LIMIT ? OFFSET ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, limit);
            ps.setInt(3, offset);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tours.add(mapRowToTour(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

    @Override
    public long countAll() {
        String sql = "SELECT COUNT(*) FROM tours";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public long countByName(String name) {
        String sql = "SELECT COUNT(*) FROM tours WHERE name ILIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    @Override
    public boolean save(Tour tour) {
        String sql = "INSERT INTO tours (name, description, price) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tour.getName());
            ps.setString(2, tour.getDescription());
            ps.setBigDecimal(3, tour.getPrice() == null ? BigDecimal.ZERO : tour.getPrice());
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Tour tour) {
        String sql = "UPDATE tours SET name = ?, description = ?, price = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tour.getName());
            ps.setString(2, tour.getDescription());
            ps.setBigDecimal(3, tour.getPrice() == null ? BigDecimal.ZERO : tour.getPrice());
            ps.setLong(4, tour.getId());
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM tours WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Tour mapRowToTour(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getLong("id"));
        tour.setName(rs.getString("name"));
        tour.setDescription(rs.getString("description"));
        tour.setPrice(rs.getBigDecimal("price"));
        return tour;
    }
}
