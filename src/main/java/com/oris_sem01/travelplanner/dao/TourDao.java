package com.oris_sem01.travelplanner.dao;

import com.oris_sem01.travelplanner.model.Tour;

import java.util.List;
import java.util.Optional;

public interface TourDao {
    List<Tour> findAll();
    Optional<Tour> findById(Long id);
    boolean save(Tour tour);
    boolean update(Tour tour);
    boolean delete(Long id);

    // Новые методы для пагинации и поиска
    List<Tour> findAll(int offset, int limit);
    long countAll();
    List<Tour> searchByName(String name, int offset, int limit);
    long countByName(String name);
}
