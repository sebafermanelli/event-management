package com.solvd.domain;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class Ticket extends BaseEntity {
    private Long cost;
    private Event event = new Event();
    private Visitor attendee = new Visitor();
    private Visitor buyer = new Visitor();
    @XmlElementWrapper(name = "presentations")
    @XmlElement(name = "presentation")
    private List<Presentation> presentations = new ArrayList<>();

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
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
