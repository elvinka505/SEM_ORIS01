package com.oris_sem01.travelplanner.service.impl;

import com.oris_sem01.travelplanner.dao.impl.ScheduleDaoImpl;
import com.oris_sem01.travelplanner.model.Schedule;
import com.oris_sem01.travelplanner.service.ScheduleService;

import java.util.List;
import java.util.Optional;

public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDaoImpl scheduleDao;

    public ScheduleServiceImpl(ScheduleDaoImpl scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public Optional<Schedule> getById(Long id) {
        return scheduleDao.findById(id);
    }

    @Override
    public List<Schedule> getAll() {
        return scheduleDao.findAll();
    }

    @Override
    public List<Schedule> getByTourId(Long tourId) {
        return scheduleDao.findByTourId(tourId);
    }

    @Override
    public boolean save(Schedule schedule) {
        return scheduleDao.save(schedule);
    }

    @Override
    public boolean update(Schedule schedule) {
        return scheduleDao.update(schedule);
    }

    @Override
    public boolean delete(Long id) {
        return scheduleDao.delete(id);
    }
}
