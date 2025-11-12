package com.oris_sem01.travelplanner.service;

import com.oris_sem01.travelplanner.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> getById(Long id);
    List<Review> getAll();
    List<Review> getByTourId(Long tourId);
    boolean save(Review review);
    boolean delete(Long id);
}
