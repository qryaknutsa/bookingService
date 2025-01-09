package org.example.businessmodule.model;

import com.google.gson.annotations.SerializedName;
import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.persistence.*;
import org.example.businessmodule.enums.TicketType;
import org.example.businessmodule.enums.tools.TicketTypeAdapter;
import org.example.businessmodule.model.tools.ZonedDateTimeConverter;

import java.io.Serializable;
import java.time.ZonedDateTime;


@Entity
@Table(name = "ticket")
public class Ticket implements Serializable {
    @Id
    @Column(nullable = false, unique = true)
    @SerializedName("id")
    private Long id;

    @Column(nullable = false, columnDefinition="TEXT")
    @SerializedName("name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates", nullable = false)
    @SerializedName("coordinates")
    private Coordinates coordinates;


    @JsonbDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    @SerializedName("creationDate")
    @Convert(converter = ZonedDateTimeConverter.class)
    private ZonedDateTime creationDate;


    @Column(nullable = false)
    @SerializedName("price")
    private int price;

    @Column(nullable = false)
    @SerializedName("discount")
    private double discount;

    @Column
    @SerializedName("refundable")
    private boolean refundable = false;

    @Enumerated(EnumType.STRING)
    @Column
    @SerializedName("type")
    @JsonbTypeAdapter(TicketTypeAdapter.class)
    private TicketType type;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "person")
    @SerializedName("person")
    private Person person;

    @Column(name = "eventId")
    private int eventId = 0;


    public Ticket() {
    }


    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
