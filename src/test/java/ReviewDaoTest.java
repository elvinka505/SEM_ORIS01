package com.oris_sem01.travelplanner.test;

import com.oris_sem01.travelplanner.config.DatabaseConfig;
import com.oris_sem01.travelplanner.dao.impl.ReviewDaoImpl;
import com.oris_sem01.travelplanner.model.Review;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class ReviewDaoTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConfig.getConnection()) {
            ReviewDaoImpl reviewDao = new ReviewDaoImpl(connection);

            Review review = new Review();
            review.setTourId(1L);
            review.setUserId(1L);
            review.setText("Тестовый отзыв");
            review.setRating(5);
            review.setCreatedDate(LocalDate.now());

            boolean result = reviewDao.save(review);
            System.out.println(result ? "Отзыв добавлен" : "Ошибка");

            reviewDao.findAll().forEach(r -> System.out.println(r.getId() + " " + r.getRating()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
