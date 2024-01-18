package com.solvd.persistence;

import com.solvd.domain.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface TicketRepository extends GenericRepository<Ticket> {
    void addPresentation(@Param("ticketId") Long ticketId, @Param("presentationId") Long presentationId);

    void removePresentation(@Param("ticketId") Long ticketId, @Param("presentationId") Long presentationId);

    Collection<Ticket> findManyByPresentationId(@Param("presentationId") Long presentationId);
}
