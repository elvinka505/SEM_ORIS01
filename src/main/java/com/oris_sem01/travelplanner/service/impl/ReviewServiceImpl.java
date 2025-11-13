package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.impl.ReviewDaoImpl;
import com.oris_sem01.travelplanner.model.Review;
import com.oris_sem01.travelplanner.service.ReviewService;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewDaoImpl reviewDao;

    public ReviewServiceImpl(ReviewDaoImpl reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public Optional<Review> getById(Long id) {
        return reviewDao.findById(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewDao.findAll();
    }

    @Override
    public List<Review> getByTourId(Long tourId) {
        return reviewDao.findByTourId(tourId);
    }

    @Override
    public boolean save(Review review) {
        return reviewDao.save(review);
    }

    @Override
    public boolean delete(Long id) {
        return reviewDao.delete(id);
    }
}
