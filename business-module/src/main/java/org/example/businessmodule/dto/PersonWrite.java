package org.example.businessmodule.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.example.businessmodule.validation.annotation.CustomNotNull;

import java.io.Serializable;

public class PersonWrite implements Serializable {

    private Integer id;

    @CustomNotNull
    @Min(value = 50, message = "Значение должно быть больше 50")
    @Max(value = 300, message = "Значение должно быть меньше 300")
    private Integer height;

    @Pattern(regexp = "(?i)^(GREEN|RED|BLUE)$", message = "Некорректный выбор цвета глаз'")
    private String eyeColor;

    @CustomNotNull
    @Pattern(regexp = "(?i)^(BLACK|RED|BLUE|ORANGE|WHITE)$", message = "Некорректный выбор цвета волос'")
    private String hairColor;

    @Pattern(regexp = "(?i)^(china|japan|north_korea)$", message = "Некорректный выбор национальности'")
    private String nationality;

    @CustomNotNull
    @Valid
    private LocationWrite location;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Min(value = 50, message = "Значение должно быть больше 50") @Max(value = 300, message = "Значение должно быть меньше 300") Integer getHeight() {
        return height;
    }

    public void setHeight(@Min(value = 50, message = "Значение должно быть больше 50") @Max(value = 300, message = "Значение должно быть меньше 300") Integer height) {
        this.height = height;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public @Valid LocationWrite getLocation() {
        return location;
    }

    public void setLocation(@Valid LocationWrite location) {
        this.location = location;
    }
}