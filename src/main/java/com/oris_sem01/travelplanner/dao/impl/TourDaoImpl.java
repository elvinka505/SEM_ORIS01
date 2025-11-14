package com.oris_sem01.travelplanner.dao.impl;

import com.oris_sem01.travelplanner.model.Tour;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDaoImpl {
    private final Connection connection;

    public TourDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Optional<Tour> findById(Long id) {
        String sql = "SELECT id, name, description, destination, price FROM tours WHERE id = ?";
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

    public List<Tour> findAll() {
        List<Tour> tours = new ArrayList<>();
        String sql = "SELECT id, name, description, destination, price FROM tours";
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

    public boolean save(Tour tour) {
        String sql = "INSERT INTO tours (name, description, destination, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tour.getName());
            ps.setString(2, tour.getDescription());
            ps.setString(3, tour.getDestination());
            ps.setBigDecimal(4, tour.getPrice());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Tour tour) {
        String sql = "UPDATE tours SET name = ?, description = ?, destination = ?, price = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, tour.getName());
            ps.setString(2, tour.getDescription());
            ps.setString(3, tour.getDestination());
            ps.setBigDecimal(4, tour.getPrice());
            ps.setLong(5, tour.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM tours WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Tour mapRowToTour(ResultSet rs) throws SQLException {
        Tour tour = new Tour();
        tour.setId(rs.getLong("id"));
        tour.setName(rs.getString("name"));
        tour.setDescription(rs.getString("description"));
        tour.setDestination(rs.getString("destination"));
        tour.setPrice(rs.getBigDecimal("price"));
        return tour;
    }
}