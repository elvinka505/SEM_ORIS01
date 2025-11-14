package com.oris_sem01.travelplanner.model;

import java.time.LocalDate;

public class Booking {
    private Long id;
    private Long userId;
    private Long tourId;
    private LocalDate bookingDate;
    private String status;

    public Booking() {}

    public Booking(Long id, Long userId, Long tourId, LocalDate bookingDate, String status) {
        this.id = id;
        this.userId = userId;
        this.tourId = tourId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getTourId() { return tourId; }
    public void setTourId(Long tourId) { this.tourId = tourId; }

    public LocalDate getBookingDate() { return bookingDate; }
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", userId=" + userId + ", tourId=" + tourId + ", status='" + status + '\'' + '}';
    }
}