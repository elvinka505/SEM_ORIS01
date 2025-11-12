package com.oris_sem01.travelplanner.service;

import com.oris_sem01.travelplanner.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {
    Optional<Schedule> getById(Long id);
    List<Schedule> getAll();
    List<Schedule> getByTourId(Long tourId);
    boolean save(Schedule schedule);
    boolean update(Schedule schedule);
    boolean delete(Long id);
}
