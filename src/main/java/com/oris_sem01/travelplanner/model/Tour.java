package com.oris_sem01.travelplanner.model;

import java.math.BigDecimal;

public class Tour {
    private Long id;
    private String name;
    private String description;
    private String destination;
    private BigDecimal price;

    public Tour() {
    }

    public Tour(Long id, String name, String description, String destination, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.destination = destination;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tour{" + "id=" + id + ", name='" + name + '\'' + ", price=" + price + '}';
    }
}