package com.solvd.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.domain.builder.PresentationBuilder;
import com.solvd.parser.JsonDateAdapter;
import com.solvd.parser.XmlDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "presentation")
@XmlAccessorType(XmlAccessType.FIELD)
public class Presentation extends BaseEntity {
    private String name;
    private String description;

    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    @JsonDeserialize(using = JsonDateAdapter.class)
    private Date startDateTime;

    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    @JsonDeserialize(using = JsonDateAdapter.class)
    private Date endDateTime;

    private BigDecimal ticketPrice;
    private Room room;
    private Presenter presenter;

    public Presentation() {
        this.room = new Room();
        this.presenter = new Presenter();
    }

    public static PresentationBuilder builder() {
        return new PresentationBuilder();
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
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
