package com.oris_sem01.travelplanner.model;

import java.time.LocalDate;

public class Review {
    private Long id;
    private Long tourId;
    private Long userId;
    private String text;
    private int rating;
    private LocalDate createdDate;

    public Review() {}

    public Review(Long id, Long tourId, Long userId, String text, int rating, LocalDate createdDate) {
        this.id = id;
        this.tourId = tourId;
        this.userId = userId;
        this.text = text;
        this.rating = rating;
        this.createdDate = createdDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTourId() { return tourId; }
    public void setTourId(Long tourId) { this.tourId = tourId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }
}