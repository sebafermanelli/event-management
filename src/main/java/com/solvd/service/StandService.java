package com.solvd.service;

import com.solvd.domain.Stand;

public interface StandService extends GenericService<Stand> {
    void addClient(Long standId, Long clientId);

    void removeClient(Long standId);
}
