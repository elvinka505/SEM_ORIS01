package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.TourDao;
import com.oris_sem01.travelplanner.model.Tour;
import com.oris_sem01.travelplanner.service.TourService;

import java.util.List;
import java.util.Optional;

public class TourServiceImpl implements TourService {

    private final TourDao tourDao;

    public TourServiceImpl(TourDao tourDao) {
        this.tourDao = tourDao;
    }

    @Override
    public Optional<Tour> getById(Long id) {
        return tourDao.findById(id);
    }

    @Override
    public List<Tour> getAll() {
        return tourDao.findAll();
    }

    @Override
    public List<Tour> getPage(int pageNumber, int pageSize) {
        int offset = (pageNumber - 1) * pageSize;
        return tourDao.findAll(offset, pageSize);
    }

    @Override
    public List<Tour> searchByName(String name, int page, int size) {
        int offset = (page - 1) * size;
        return tourDao.searchByName(name, offset, size);
    }

    @Override
    public long getCount() {
        return tourDao.countAll();
    }

    @Override
    public long getCountByName(String name) {
        return tourDao.countByName(name);
    }

    @Override
    public boolean save(Tour tour) {
        return tourDao.save(tour);
    }

    @Override
    public boolean update(Tour tour) {
        return tourDao.update(tour);
    }

    @Override
    public boolean delete(Long id) {
        return tourDao.delete(id);
    }
}
