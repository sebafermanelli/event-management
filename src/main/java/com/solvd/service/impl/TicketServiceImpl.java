package com.solvd.service.impl;

import com.solvd.domain.Event;
import com.solvd.domain.Presentation;
import com.solvd.domain.Ticket;
import com.solvd.domain.Visitor;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.TicketRepository;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.service.TicketService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final EventServiceImpl eventService;
    private final VisitorServiceImpl visitorService;
    private final PresentationServiceImpl presentationService;

    public TicketServiceImpl(String dbType, String type) {
        this.ticketRepository = AbstractRepositoryFactory.createFactory(dbType).createTicketRepository(type);
        this.eventService = new EventServiceImpl(dbType, type);
        this.visitorService = new VisitorServiceImpl(dbType, type);
        this.presentationService = new PresentationServiceImpl(dbType, type);
    }

    @Override
    public void create(Ticket ticket) {
        Optional<Ticket> optionalTicket = this.getById(ticket.getId());
        if (optionalTicket.isPresent()) {
            throw new EntityAlreadyExistException("The ticket with the id " + ticket.getId() + "already exist");
        }
        Optional<Event> optionalEvent = eventService.getById(ticket.getEvent().getId());
        if (optionalEvent.isEmpty()) {
            throw new ResourceNotFoundException("The event does not exist");
        }
        Optional<Visitor> optionalAttendee = visitorService.getById(ticket.getAttendee().getId());
        if (optionalAttendee.isEmpty()) {
            visitorService.create(ticket.getAttendee());
        }
        Optional<Visitor> optionalBuyer = visitorService.getById(ticket.getBuyer().getId());
        if (optionalBuyer.isEmpty()) {
            visitorService.create(ticket.getBuyer());
        }
        final BigDecimal[] presentationsCost = {BigDecimal.ZERO};
        ticket.getPresentations().forEach(presentation -> {
            Optional<Presentation> optionalPresentation = presentationService.getById(presentation.getId());
            if (optionalPresentation.isPresent()) {
                this.addPresentation(ticket.getId(), optionalPresentation.get().getId());
                BigDecimal presentationPrice = optionalPresentation.get().getTicketPrice();

                presentationsCost[0] = presentationsCost[0].add(presentationPrice);
            }
        });
        ticket.setCost(ticket.getEvent().getBaseTicketPrice().add(presentationsCost[0]));
        ticketRepository.save(ticket);
    }

    @Override
    public Collection<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        return ticketRepository.findById(id);
    }

    @Override
    public Collection<Ticket> getManyByColumn(String key, String value) {
        return ticketRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Ticket> getOneByColumn(String key, String value) {
        return ticketRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public void updateById(Ticket ticket, Long id) {
        ticketRepository.updateById(ticket, id);
    }

    @Override
    public void addPresentation(Long ticketId, Long presentationId) {
        Optional<Ticket> optionalTicket = this.getById(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new ResourceNotFoundException("The ticket does not exist");
        }
        Optional<Presentation> optionalPresentation = presentationService.getById(presentationId);
        if (optionalPresentation.isEmpty()) {
            throw new ResourceNotFoundException("The presentation does not exist");
        }
        ticketRepository.addPresentation(ticketId, presentationId);
        optionalTicket.get().getPresentations().add(optionalPresentation.get());
        optionalTicket.get().setCost(optionalTicket.get().getCost().add(optionalPresentation.get().getTicketPrice()));
        ticketRepository.updateById(optionalTicket.get(), optionalTicket.get().getId());
    }

    @Override
    public void removePresentation(Long ticketId, Long presentationId) {
        Optional<Ticket> optionalTicket = this.getById(ticketId);
        if (optionalTicket.isEmpty()) {
            throw new ResourceNotFoundException("The ticket does not exist");
        }
        Optional<Presentation> optionalPresentation = presentationService.getById(presentationId);
        if (optionalPresentation.isEmpty()) {
            throw new ResourceNotFoundException("The presentation does not exist");
        }
        ticketRepository.removePresentation(ticketId, presentationId);
        optionalTicket.get().getPresentations().remove(optionalPresentation.get());
        optionalTicket.get().setCost(optionalTicket.get().getCost().subtract(optionalPresentation.get().getTicketPrice()));
        ticketRepository.updateById(optionalTicket.get(), optionalTicket.get().getId());
    }

    @Override
    public Collection<Ticket> getManyByPresentationId(Long presentationId) {
        return ticketRepository.findManyByPresentationId(presentationId);
    }
}
