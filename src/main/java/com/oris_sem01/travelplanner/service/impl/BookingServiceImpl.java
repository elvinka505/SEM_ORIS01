package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.impl.BookingDaoImpl;
import com.oris_sem01.travelplanner.model.Booking;
import java.util.List;
import java.util.Optional;

public class BookingServiceImpl {
    private final BookingDaoImpl bookingDao;

    public BookingServiceImpl(BookingDaoImpl bookingDao) {
        this.bookingDao = bookingDao;
    }

    public Optional<Booking> getById(Long id) {
        return bookingDao.findById(id);
    }

    public List<Booking> getAll() {
        return bookingDao.findAll();
    }

    public boolean save(Booking booking) {
        return bookingDao.save(booking);
    }

    public boolean delete(Long id) {
        return bookingDao.delete(id);
    }
}