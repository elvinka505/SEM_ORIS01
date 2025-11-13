package com.oris_sem01.travelplanner.dao;

import com.oris_sem01.travelplanner.model.Schedule;
import java.util.List;
import java.util.Optional;

public interface ScheduleDao {
    Optional<Schedule> findById(Long id);
    List<Schedule> findAll();
    List<Schedule> findByTourId(Long tourId);
    boolean save(Schedule schedule);
    boolean update(Schedule schedule);
    boolean delete(Long id);
}
