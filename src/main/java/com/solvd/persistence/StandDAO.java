package com.solvd.persistence;

import com.solvd.domain.Stand;

public interface StandDAO extends GenericDAO<Stand> {
    void save(Stand entity, Long roomId);
    void addClient(Long standId, Long clientId);
}
