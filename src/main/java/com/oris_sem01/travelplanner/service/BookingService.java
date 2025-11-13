package com.oris_sem01.travelplanner.service;

import com.oris_sem01.travelplanner.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    Optional<Booking> getById(Long id);
    List<Booking> getAll();
    boolean save(Booking booking);
    boolean delete(Long id);
}
