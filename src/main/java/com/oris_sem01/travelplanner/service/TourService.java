package com.oris_sem01.travelplanner.service;

import com.oris_sem01.travelplanner.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {
    Optional<Tour> getById(Long id);
    List<Tour> getAll();
    List<Tour> getPage(int pageNumber, int pageSize);
    List<Tour> searchByName(String name, int page, int size);
    long getCount();
    long getCountByName(String name);
    boolean save(Tour tour);
    boolean update(Tour tour);
    boolean delete(Long id);
}
