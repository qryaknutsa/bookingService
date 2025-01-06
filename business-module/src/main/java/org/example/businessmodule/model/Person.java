package org.example.businessmodule.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.example.businessmodule.enums.Country;
import org.example.businessmodule.enums.EyeColor;
import org.example.businessmodule.enums.HairColor;

import java.io.Serializable;

@Entity
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    @JsonProperty("id")
    private int id;

    @Column(nullable = false)
    @JsonProperty("height")
    private int height;

    @Enumerated(EnumType.STRING)
    @Column(name = "eyeColor")
    @JsonProperty("eyeColor")
    private EyeColor eyeColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "hairColor", nullable = false)
    @JsonProperty("hairColor")
    private HairColor hairColor;


    @Enumerated(EnumType.STRING)
    @Column
    @JsonProperty("nationality")
    private Country nationality;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "location", nullable = false)
    @JsonProperty("location")
    private Location location;

    public Person() {
    }

    public Person(int height, EyeColor eyeColor, HairColor hairColor, Country nationality, Location location) {
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Person(int height, EyeColor eyeColor, HairColor hairColor, Country nationality) {
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
