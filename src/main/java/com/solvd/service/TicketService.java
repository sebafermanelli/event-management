package com.solvd.service;

import com.solvd.domain.Ticket;

import java.util.Collection;

public interface TicketService extends GenericService<Ticket> {
    void addPresentation(Long ticketId, Long presentationId);

    void removePresentation(Long ticketId, Long presentationId);

    Collection<Ticket> getManyByPresentationId(Long presentationId);
}
