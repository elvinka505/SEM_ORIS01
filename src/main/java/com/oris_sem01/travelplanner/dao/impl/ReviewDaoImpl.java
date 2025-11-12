package com.oris_sem01.travelplanner.dao.impl;

import com.oris_sem01.travelplanner.dao.ReviewDao;
import com.oris_sem01.travelplanner.model.Review;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDaoImpl implements ReviewDao {

    private final Connection connection;

    public ReviewDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Review> findById(Long id) {
        String sql = "SELECT id, tourid, userid, text, rating, createddate FROM reviews WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRowToReview(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT id, tourid, userid, text, rating, createddate FROM reviews";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reviews.add(mapRowToReview(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<Review> findByTourId(Long tourId) {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT id, tourid, userid, text, rating, createddate FROM reviews WHERE tourid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, tourId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    reviews.add(mapRowToReview(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public boolean save(Review review) {
        String sql = "INSERT INTO reviews (tourid, userid, text, rating, createddate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, review.getTourId());
            ps.setLong(2, review.getUserId());
            ps.setString(3, review.getText());
            ps.setInt(4, review.getRating());
            ps.setDate(5, java.sql.Date.valueOf(review.getCreatedDate()));
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Review review) {
        String sql = "UPDATE reviews SET tourid = ?, userid = ?, text = ?, rating = ?, createddate = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, review.getTourId());
            ps.setLong(2, review.getUserId());
            ps.setString(3, review.getText());
            ps.setInt(4, review.getRating());
            ps.setDate(5, java.sql.Date.valueOf(review.getCreatedDate()));
            ps.setLong(6, review.getId());
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM reviews WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int rows = ps.executeUpdate();
            return rows == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Review mapRowToReview(ResultSet rs) throws SQLException {
        Review review = new Review();
        review.setId(rs.getLong("id"));
        review.setTourId(rs.getLong("tourid"));
        review.setUserId(rs.getLong("userid"));
        review.setText(rs.getString("text"));
        review.setRating(rs.getInt("rating"));
        review.setCreatedDate(rs.getDate("createddate").toLocalDate());
        return review;
    }
}
