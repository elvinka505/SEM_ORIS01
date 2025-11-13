package com.oris_sem01.travelplanner.dao;

import com.oris_sem01.travelplanner.model.Review;
import java.util.List;
import java.util.Optional;

public interface ReviewDao {
    Optional<Review> findById(Long id);
    List<Review> findAll();
    List<Review> findByTourId(Long tourId);
    boolean save(Review review);
    boolean update(Review review);
    boolean delete(Long id);
}
