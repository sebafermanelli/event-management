package com.solvd.domain.builder;

import com.solvd.domain.Event;
import com.solvd.domain.Presentation;
import com.solvd.domain.Ticket;
import com.solvd.domain.Visitor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TicketBuilder extends BaseEntityBuilder<TicketBuilder, Ticket> {
    private BigDecimal cost;
    private Event event = new Event();
    private Visitor attendee = new Visitor();
    private Visitor buyer = new Visitor();
    private List<Presentation> presentations = new ArrayList<>();

    public TicketBuilder cost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public TicketBuilder event(Event event) {
        this.event = event;
        return this;
    }

    public TicketBuilder attendee(Visitor attendee) {
        this.attendee = attendee;
        return this;
    }

    public TicketBuilder buyer(Visitor buyer) {
        this.buyer = buyer;
        return this;
    }

    public TicketBuilder presentations(List<Presentation> presentations) {
        this.presentations = presentations;
        return this;
    }

    @Override
    public Ticket build() {
        Ticket ticket = new Ticket();
        ticket.setId(id);
        ticket.setCost(cost);
        ticket.setEvent(event);
        ticket.setAttendee(attendee);
        ticket.setBuyer(buyer);
        ticket.setPresentations(presentations);
        return ticket;
    }
}
