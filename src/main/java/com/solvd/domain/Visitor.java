package com.solvd.domain;

import java.util.List;

public class Visitor extends Person {
    private List<Ticket> attendeeTickets;
    private List<Ticket> buyerTickets;

    public List<Ticket> getAttendeeTickets() {
        return attendeeTickets;
    }

    public void setAttendeeTickets(List<Ticket> attendeeTickets) {
        this.attendeeTickets = attendeeTickets;
    }

    public List<Ticket> getBuyerTickets() {
        return buyerTickets;
    }

    public void setBuyerTickets(List<Ticket> buyerTickets) {
        this.buyerTickets = buyerTickets;
    }
}
