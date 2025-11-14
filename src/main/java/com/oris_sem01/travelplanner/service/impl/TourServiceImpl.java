package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.impl.TourDaoImpl;
import com.oris_sem01.travelplanner.model.Tour;
import java.util.List;
import java.util.Optional;

public class TourServiceImpl {
    private final TourDaoImpl tourDao;

    public TourServiceImpl(TourDaoImpl tourDao) {
        this.tourDao = tourDao;
    }

    public Optional<Tour> getById(Long id) {
        return tourDao.findById(id);
    }

    public List<Tour> getAll() {
        return tourDao.findAll();
    }

    public boolean save(Tour tour) {
        return tourDao.save(tour);
    }

    public boolean update(Tour tour) {
        return tourDao.update(tour);
    }

    public boolean delete(Long id) {
        return tourDao.delete(id);
    }
}