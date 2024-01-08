package com.solvd.persistence;

import com.solvd.domain.Ticket;

public interface TicketDAO extends GenericDAO<Ticket> {
    void save(Ticket entity, Long eventId, Long attendeeId, Long buyerId);

    void addPresentation(Long ticketId, Long presentationId);
}
