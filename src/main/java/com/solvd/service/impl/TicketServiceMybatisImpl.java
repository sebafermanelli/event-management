package com.solvd.service.impl;

import com.solvd.domain.Ticket;
import com.solvd.persistence.impl.TicketDAOMybatisImpl;
import com.solvd.service.TicketService;

import java.util.Collection;
import java.util.Optional;

public class TicketServiceMybatisImpl implements TicketService {
    private static final TicketDAOMybatisImpl ticketDAOMybatis = new TicketDAOMybatisImpl();

    @Override
    public void create(Ticket ticket) {
        ticketDAOMybatis.save(ticket);
    }

    @Override
    public Collection<Ticket> getAll() {
        return ticketDAOMybatis.findAll();
    }

    @Override
    public Optional<Ticket> getById(Long id) {
        return ticketDAOMybatis.findById(id);
    }

    @Override
    public Collection<Ticket> getManyByColumn(String key, String value) {
        return ticketDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Ticket> getOneByColumn(String key, String value) {
        return ticketDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        ticketDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Ticket ticket, Long id) {
        ticketDAOMybatis.updateById(ticket, id);
    }

    @Override
    public void addPresentation(Long ticketId, Long presentationId) {
        ticketDAOMybatis.addPresentation(ticketId, presentationId);
    }

    @Override
    public void removePresentation(Long ticketId, Long presentationId) {
        ticketDAOMybatis.removePresentation(ticketId, presentationId);
    }

    @Override
    public Collection<Ticket> getManyByPresentationId(Long presentationId) {
        return ticketDAOMybatis.findManyByPresentationId(presentationId);
    }
}
