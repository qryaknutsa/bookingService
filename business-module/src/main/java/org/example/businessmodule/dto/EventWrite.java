package org.example.businessmodule.dto;

import com.google.gson.annotations.SerializedName;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.example.businessmodule.validation.annotation.CustomNotNull;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class EventWrite implements Serializable {
    @CustomNotNull
    @Size(min = 1, message = "Значение должно быть от 1 до 2147483647 символов")
    private String title;

    private String description;

    @CustomNotNull
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    @SerializedName("startTime")
    @FutureOrPresent(message = "Время начала не может быть в прошлом")
    private ZonedDateTime startTime;

    @CustomNotNull
    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    @SerializedName("endTime")
    @FutureOrPresent(message = "Время окончания не может быть в прошлом")
    private ZonedDateTime endTime;

    @CustomNotNull
    @Valid
    private CoordinatesWrite coordinates;

    @CustomNotNull
    @DecimalMax(value = "2147483647", message = "Значение не может быть больше возможного 2147483647")
    @DecimalMin(value = "1", message = "Значение не может быть меньше возможного 1")
    private Integer price;

    @CustomNotNull
    @DecimalMin(value = "0", message = "Значение не может быть меньше возможного 0")
    @DecimalMax(value = "100", message = "Значение не может быть больше возможного 100")
    private Double discount;

    @CustomNotNull
    @Valid
    private LocationWrite location;

    @CustomNotNull
    @Positive(message = "Значение должен быть больше нуля")
//    @DecimalMax(value = "100", message = "Значение не может быть больше 100")
    @SerializedName("ticketsNum")
    private Integer ticketsNum;

    public @DecimalMax(value = "2147483647", message = "Значение не может быть больше возможного 2147483647") @DecimalMin(value = "1", message = "Значение не может быть меньше возможного 1") Integer getPrice() {
        return price;
    }

    public void setPrice(@DecimalMax(value = "2147483647", message = "Значение не может быть больше возможного 2147483647") @DecimalMin(value = "1", message = "Значение не может быть меньше возможного 1") Integer price) {
        this.price = price;
    }

    public @Size(min = 1, message = "Значение должно быть от 1 до 2147483647 символов") String getTitle() {
        return title;
    }

    public void setTitle(@Size(min = 1, message = "Значение должно быть от 1 до 2147483647 символов") String title) {
        this.title = title;
    }

    public EventWrite() {
    }

    public EventWrite(String title, ZonedDateTime startTime, ZonedDateTime endTime, CoordinatesWrite coordinates, Integer price, Double discount, Integer ticketsNum) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coordinates = coordinates;
        this.price = price;
        this.discount = discount;
        this.ticketsNum = ticketsNum;
    }



    public @Valid LocationWrite getLocation() {
        return location;
    }

    public void setLocation(@Valid LocationWrite location) {
        this.location = location;
    }

    public EventWrite(String title, String description, ZonedDateTime startTime, ZonedDateTime endTime, CoordinatesWrite coordinates, Integer price, Double discount) {
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coordinates = coordinates;
        this.price = price;
        this.discount = discount;
        this.ticketsNum = ticketsNum;
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

    public @Valid CoordinatesWrite getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(@Valid CoordinatesWrite coordinates) {
        this.coordinates = coordinates;
    }


    public @DecimalMin(value = "0", message = "Значение не может быть меньше возможного 0") @DecimalMax(value = "100", message = "Значение не может быть больше возможного 100") Double getDiscount() {
        return discount;
    }

    public void setDiscount(@DecimalMin(value = "0", message = "Значение не может быть меньше возможного 0") @DecimalMax(value = "100", message = "Значение не может быть больше возможного 100") Double discount) {
        this.discount = discount;
    }

    public @Positive(message = "Значение должен быть больше нуля") @Max(value = 2147483647, message = "Значение не может быть больше возможного 2147483647") Integer getTicketsNum() {
        return ticketsNum;
    }

    public void setTicketsNum(@Positive(message = "Значение должен быть больше нуля") @Max(value = 2147483647, message = "Значение не может быть больше возможного 2147483647") Integer ticketsNum) {
        this.ticketsNum = ticketsNum;
    }



}
