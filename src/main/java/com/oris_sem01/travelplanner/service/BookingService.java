package com.oris_sem01.travelplanner.service;

import com.oris_sem01.travelplanner.model.Booking;
import com.oris_sem01.travelplanner.repository.BookingRepository;

import java.util.List;
import java.util.Objects;

public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = Objects.requireNonNull(bookingRepository);
    }

    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    public List<Booking> findByUserId(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId is null");
        }
        return bookingRepository.findByUserId(userId);
    }

    public void create(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("booking is null");
        }
        if (booking.getUserId() == null || booking.getTourId() == null) {
            throw new IllegalArgumentException("userId и tourId обязательны");
        }
        bookingRepository.create(booking);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        bookingRepository.delete(id);
    }

    public void create(Long id, long tourId) {
    }
}
