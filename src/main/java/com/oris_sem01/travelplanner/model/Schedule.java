package com.oris_sem01.travelplanner.model;

import java.time.LocalDate;

public class Schedule {
    private Long id;
    private Long tourId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String location;
    private String description;
    private int availableSeats;

    public Schedule() {}

    public Schedule(Long id, Long tourId, LocalDate startDate, LocalDate endDate,
                    String location, String description, int availableSeats) {
        this.id = id;
        this.tourId = tourId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.description = description;
        this.availableSeats = availableSeats;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTourId() { return tourId; }
    public void setTourId(Long tourId) { this.tourId = tourId; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }
}