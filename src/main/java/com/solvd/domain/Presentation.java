package com.solvd.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.parser.JsonLocalDateAdapter;
import com.solvd.parser.XmlLocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlRootElement(name = "presentation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Presentation extends BaseEntity {
    private String name;
    private String description;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonDeserialize(using = JsonLocalDateAdapter.class)
    private LocalDate startDateTime;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonDeserialize(using = JsonLocalDateAdapter.class)
    private LocalDate endDateTime;
    private Long ticketPrice;
    private Room room = new Room();
    private Presenter presenter = new Presenter();

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

    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDate startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDate getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDate endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Long getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Long ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }
}
