package com.oris_sem01.travelplanner.dao.impl;

import com.oris_sem01.travelplanner.dao.BookingDao;
import com.oris_sem01.travelplanner.model.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDaoImpl implements BookingDao {

    private final Connection connection;

    public BookingDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Booking> findById(Long id) {
        String sql = "SELECT id, userid, tourid, bookingdate, status FROM bookings WHERE id = ?";
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

    @Override
    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, userid, tourid, bookingdate, status FROM bookings";
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

    @Override
    public boolean save(Booking booking) {
        String sql = "INSERT INTO bookings (userid, tourid, bookingdate, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, booking.getUserId());
            ps.setLong(2, booking.getTourId());
            ps.setDate(3, java.sql.Date.valueOf(booking.getBookingDate()));
            ps.setString(4, booking.getStatus() != null ? booking.getStatus() : "pending");
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Booking booking) {
        String sql = "UPDATE bookings SET userid = ?, tourid = ?, bookingdate = ?, status = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, booking.getUserId());
            ps.setLong(2, booking.getTourId());
            ps.setDate(3, java.sql.Date.valueOf(booking.getBookingDate()));
            ps.setString(4, booking.getStatus() != null ? booking.getStatus() : "pending");
            ps.setLong(5, booking.getId());
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Booking mapRowToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setId(rs.getLong("id"));
        booking.setUserId(rs.getLong("userid"));
        booking.setTourId(rs.getLong("tourid"));
        booking.setBookingDate(rs.getDate("bookingdate").toLocalDate());
        booking.setStatus(rs.getString("status"));
        return booking;
    }
}
