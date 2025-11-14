package com.oris_sem01.travelplanner.dao.impl;

import com.oris_sem01.travelplanner.model.Booking;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDaoImpl {
    private final Connection connection;

    public BookingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public Optional<Booking> findById(Long id) {
        String sql = "SELECT id, user_id, tour_id, booking_date, status FROM bookings WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToBooking(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, user_id, tour_id, booking_date, status FROM bookings";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bookings.add(mapRowToBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public boolean save(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, tour_id, booking_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, booking.getUserId());
            ps.setLong(2, booking.getTourId());
            ps.setDate(3, Date.valueOf(booking.getBookingDate()));
            ps.setString(4, booking.getStatus() != null ? booking.getStatus() : "pending");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Booking booking) {
        String sql = "UPDATE bookings SET user_id = ?, tour_id = ?, booking_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, booking.getUserId());
            ps.setLong(2, booking.getTourId());
            ps.setDate(3, Date.valueOf(booking.getBookingDate()));
            ps.setString(4, booking.getStatus());
            ps.setLong(5, booking.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Long id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Booking mapRowToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getLong("id"));
        booking.setUserId(rs.getLong("user_id"));
        booking.setTourId(rs.getLong("tour_id"));
        booking.setBookingDate(rs.getDate("booking_date").toLocalDate());
        booking.setStatus(rs.getString("status"));
        return booking;
    }
}