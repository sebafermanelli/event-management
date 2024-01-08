package com.solvd.persistence;

import com.solvd.domain.Presentation;

public interface PresentationDAO extends GenericDAO<Presentation> {
    void save(Presentation entity, Long roomId, Long presenterId);
}
