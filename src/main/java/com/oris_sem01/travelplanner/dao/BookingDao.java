package com.oris_sem01.travelplanner.dao;

import com.oris_sem01.travelplanner.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingDao {
    List<Booking> findAll();
    Optional<Booking> findById(Long id);
    boolean save(Booking booking);
    boolean update(Booking booking);
    boolean delete(Long id);
}
