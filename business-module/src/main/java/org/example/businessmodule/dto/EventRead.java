package org.example.businessmodule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Convert;
import org.example.businessmodule.model.tools.ZonedDateTimeConverter;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class EventRead implements Serializable {
    private int id;
    private String title;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonProperty("startTime")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @JsonProperty("endTime")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime endTime;

    private CoordinatesWrite coordinates;


    private LocationWrite location;

    private Integer price;

    private Double discount;


    @JsonProperty("ticketsNum")
    private Integer ticketsNum;

    public EventRead(int id, String title, String description, ZonedDateTime startTime, ZonedDateTime endTime, CoordinatesWrite coordinates, Integer price, Double discount, Integer ticketsNum) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coordinates = coordinates;
        this.price = price;
        this.discount = discount;
        this.ticketsNum = ticketsNum;
    }

    public EventRead() {
    }

    public LocationWrite getLocation() {
        return location;
    }

    public void setLocation(LocationWrite location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public CoordinatesWrite getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(CoordinatesWrite coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getTicketsNum() {
        return ticketsNum;
    }

    public void setTicketsNum(Integer ticketsNum) {
        this.ticketsNum = ticketsNum;
    }
}