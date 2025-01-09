package org.example.businessmodule.model;

import com.google.gson.annotations.SerializedName;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;


@Entity
@Table(name = "event")
public class Event implements Serializable {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, columnDefinition="TEXT")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "startTime", nullable = false, columnDefinition = "TIMESTAMP(0) WITH TIME ZONE")
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @SerializedName("startTime")
    private ZonedDateTime startTime;

    @Column(name = "endTime", nullable = false, columnDefinition = "TIMESTAMP(0) WITH TIME ZONE")
    @JsonbDateFormat(value = "yyyy-MM-dd'T'HH:mm:ssXXX")
    @SerializedName("endTime")
    private ZonedDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates", nullable = false)
    private Coordinates coordinates;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location", nullable = false)
    private Location location;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "discount",nullable = false)
    @SerializedName("discount")
    private Double discount;


    public Event() {
    }

    public Event(String title, String description, ZonedDateTime startTime, ZonedDateTime endTime, Coordinates coordinates, Integer price, Double discount) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coordinates = coordinates;
        this.price = price;
        this.discount = discount;
    }

    public Event(String title, String description, ZonedDateTime startTime, ZonedDateTime endTime, Coordinates coordinates, Location location, Integer price, Double discount) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coordinates = coordinates;
        this.location = location;
        this.price = price;
        this.discount = discount;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Event(String title, String description) {
        this.title = title;
        this.description = description;
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


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
