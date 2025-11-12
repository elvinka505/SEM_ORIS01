package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.impl.BookingDaoImpl;
import com.oris_sem01.travelplanner.model.Booking;
import com.oris_sem01.travelplanner.service.BookingService;

import java.util.List;
import java.util.Optional;

public class BookingServiceImpl implements BookingService {

    private final BookingDaoImpl bookingDao;

    public BookingServiceImpl(BookingDaoImpl bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public Optional<Booking> getById(Long id) {
        return bookingDao.findById(id);
    }

    @Override
    public List<Booking> getAll() {
        return bookingDao.findAll();
    }

    @Override
    public boolean save(Booking booking) {
        return bookingDao.save(booking);
    }

    @Override
    public boolean delete(Long id) {
        return bookingDao.delete(id);
    }
}
