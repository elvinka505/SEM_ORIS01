package com.oris_sem01.travelplanner.dao.impl;

import com.oris_sem01.travelplanner.dao.ScheduleDao;
import com.oris_sem01.travelplanner.model.Schedule;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScheduleDaoImpl implements ScheduleDao {

    private final Connection connection;

    public ScheduleDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT id, tourid, startdate, enddate, location, description, availableseats FROM schedules WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToSchedule(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Schedule> findAll() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT id, tourid, startdate, enddate, location, description, availableseats FROM schedules";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                schedules.add(mapRowToSchedule(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public List<Schedule> findByTourId(Long tourId) {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT id, tourid, startdate, enddate, location, description, availableseats FROM schedules WHERE tourid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, tourId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    schedules.add(mapRowToSchedule(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }

    @Override
    public boolean save(Schedule schedule) {
        String sql = "INSERT INTO schedules (tourid, startdate, enddate, location, description, availableseats) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, schedule.getTourId());
            ps.setDate(2, java.sql.Date.valueOf(schedule.getStartDate()));
            ps.setDate(3, java.sql.Date.valueOf(schedule.getEndDate()));
            ps.setString(4, schedule.getLocation());
            ps.setString(5, schedule.getDescription());
            ps.setInt(6, schedule.getAvailableSeats());
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Schedule schedule) {
        String sql = "UPDATE schedules SET tourid = ?, startdate = ?, enddate = ?, location = ?, description = ?, availableseats = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, schedule.getTourId());
            ps.setDate(2, java.sql.Date.valueOf(schedule.getStartDate()));
            ps.setDate(3, java.sql.Date.valueOf(schedule.getEndDate()));
            ps.setString(4, schedule.getLocation());
            ps.setString(5, schedule.getDescription());
            ps.setInt(6, schedule.getAvailableSeats());
            ps.setLong(7, schedule.getId());
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM schedules WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Schedule mapRowToSchedule(ResultSet rs) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setId(rs.getLong("id"));
        schedule.setTourId(rs.getLong("tourid"));
        schedule.setStartDate(rs.getDate("startdate").toLocalDate());
        schedule.setEndDate(rs.getDate("enddate").toLocalDate());
        schedule.setLocation(rs.getString("location"));
        schedule.setDescription(rs.getString("description"));
        schedule.setAvailableSeats(rs.getInt("availableseats"));
        return schedule;
    }
}
