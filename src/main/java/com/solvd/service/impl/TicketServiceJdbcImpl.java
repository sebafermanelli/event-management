package com.solvd.service.impl;

import com.solvd.domain.Event;
import com.solvd.domain.Presentation;
import com.solvd.domain.Ticket;
import com.solvd.domain.Visitor;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.EventDAOJdbcImpl;
import com.solvd.persistence.impl.PresentationDAOJdbcImpl;
import com.solvd.persistence.impl.TicketDAOJdbcImpl;
import com.solvd.persistence.impl.VisitorDAOJdbcImpl;
import com.solvd.service.TicketService;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TicketServiceJdbcImpl implements TicketService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
    private static final EventDAOJdbcImpl eventDAOJdbc = new EventDAOJdbcImpl(connection);
    private static final VisitorDAOJdbcImpl visitorDAOJdbc = new VisitorDAOJdbcImpl(connection);
    private static final PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);

    public TicketServiceJdbcImpl() {
    }

    @Override
    public void create(Ticket ticket) {
        ticketDAOJdbc.save(ticket);
    }

    @Override
    public Collection<Ticket> getAll() {
        Collection<Ticket> tickets = ticketDAOJdbc.findAll();
        tickets.forEach(ticket -> {
            Optional<Event> event = eventDAOJdbc.findById(ticket.getEvent().getId());
            event.ifPresent(ticket::setEvent);
            Optional<Visitor> attendee = visitorDAOJdbc.findById(ticket.getAttendee().getId());
            attendee.ifPresent(ticket::setAttendee);
            Optional<Visitor> buyer = visitorDAOJdbc.findById(ticket.getBuyer().getId());
            buyer.ifPresent(ticket::setBuyer);
            Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(ticket.getId());
            ticket.setPresentations((List<Presentation>) presentations);
        });
        return tickets;
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        Optional<Ticket> ticket = ticketDAOJdbc.findById(id);
        ticket.ifPresent(t -> {
            Optional<Event> event = eventDAOJdbc.findById(t.getEvent().getId());
            event.ifPresent(t::setEvent);
            Optional<Visitor> attendee = visitorDAOJdbc.findById(t.getAttendee().getId());
            attendee.ifPresent(t::setAttendee);
            Optional<Visitor> buyer = visitorDAOJdbc.findById(t.getBuyer().getId());
            buyer.ifPresent(t::setBuyer);
            Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(t.getId());
            t.setPresentations((List<Presentation>) presentations);
        });
        return ticket;
    }

    @Override
    public Collection<Ticket> getManyByColumn(String key, String value) {
        Collection<Ticket> tickets = ticketDAOJdbc.findManyByColumn(key, value);
        tickets.forEach(ticket -> {
            Optional<Event> event = eventDAOJdbc.findById(ticket.getEvent().getId());
            event.ifPresent(ticket::setEvent);
            Optional<Visitor> attendee = visitorDAOJdbc.findById(ticket.getAttendee().getId());
            attendee.ifPresent(ticket::setAttendee);
            Optional<Visitor> buyer = visitorDAOJdbc.findById(ticket.getBuyer().getId());
            buyer.ifPresent(ticket::setBuyer);
            Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(ticket.getId());
            ticket.setPresentations((List<Presentation>) presentations);
        });
        return tickets;
    }

    @Override
    public Optional<Ticket> getOneByColumn(String key, String value) {
        Optional<Ticket> ticket = ticketDAOJdbc.findOneByColumn(key, value);
        ticket.ifPresent(t -> {
            Optional<Event> event = eventDAOJdbc.findById(t.getEvent().getId());
            event.ifPresent(t::setEvent);
            Optional<Visitor> attendee = visitorDAOJdbc.findById(t.getAttendee().getId());
            attendee.ifPresent(t::setAttendee);
            Optional<Visitor> buyer = visitorDAOJdbc.findById(t.getBuyer().getId());
            buyer.ifPresent(t::setBuyer);
            Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(t.getId());
            t.setPresentations((List<Presentation>) presentations);
        });
        return ticket;
    }

    @Override
    public void deleteById(Long id) {
        ticketDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Ticket ticket, Long id) {
        ticketDAOJdbc.updateById(ticket, id);
    }

    @Override
    public void addPresentation(Long ticketId, Long presentationId) {

    }

    @Override
    public void removePresentation(Long ticketId, Long presentationId) {

    }

    @Override
    public Collection<Ticket> getManyByPresentationId(Long presentationId) {
        Collection<Ticket> tickets = ticketDAOJdbc.findManyByPresentationId(presentationId);
        tickets.forEach(ticket -> {
            Optional<Event> event = eventDAOJdbc.findById(ticket.getEvent().getId());
            event.ifPresent(ticket::setEvent);
            Optional<Visitor> attendee = visitorDAOJdbc.findById(ticket.getAttendee().getId());
            attendee.ifPresent(ticket::setAttendee);
            Optional<Visitor> buyer = visitorDAOJdbc.findById(ticket.getBuyer().getId());
            buyer.ifPresent(ticket::setBuyer);
            Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(ticket.getId());
            ticket.setPresentations((List<Presentation>) presentations);
        });
        return tickets;
    }
}
