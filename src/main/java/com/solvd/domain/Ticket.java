package com.solvd.domain;

import com.solvd.domain.builder.TicketBuilder;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket extends BaseEntity {
    private BigDecimal cost;
    private Event event;
    private Visitor attendee;
    private Visitor buyer;

    @XmlElementWrapper(name = "presentations")
    @XmlElement(name = "presentation")
    private List<Presentation> presentations;

    public Ticket() {
        this.event = new Event();
        this.attendee = new Visitor();
        this.buyer = new Visitor();
        this.presentations = new ArrayList<>();
    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Visitor getAttendee() {
        return attendee;
    }

    public void setAttendee(Visitor attendee) {
        this.attendee = attendee;
    }

    public Visitor getBuyer() {
        return buyer;
    }

    public void setBuyer(Visitor buyer) {
        this.buyer = buyer;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }
}
