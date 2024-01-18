package com.solvd.persistence;

import com.solvd.domain.Presentation;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface PresentationRepository extends GenericRepository<Presentation> {
    Collection<Presentation> findManyByTicketId(@Param("ticketId") Long ticketId);
}
