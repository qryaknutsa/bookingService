package org.example.businessmodule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "coordinates")
public class Coordinates implements Serializable {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private int id;

    @Column
    @JsonProperty("x")
    private float x;

    @Column(nullable = false)
    @JsonProperty("y")
    private Float y;

    public Coordinates() {
    }

    public Coordinates(float x, Float y) {
        this.x = x;
        this.y = y;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }
}
