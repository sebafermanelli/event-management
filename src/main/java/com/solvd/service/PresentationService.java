package com.solvd.service;

import com.solvd.domain.Presentation;

import java.util.Collection;

public interface PresentationService extends GenericService<Presentation> {
    Collection<Presentation> getManyByTicketId(Long ticketId);
}
