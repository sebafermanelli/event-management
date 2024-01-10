package com.solvd.service;

import com.solvd.domain.Event;

import java.util.Collection;

public interface EventService extends GenericService<Event> {
    void addEmployee(Long eventId, Long employeeId);

    void removeEmployee(Long eventId, Long employeeId);

    Collection<Event> getManyByEmployeeId(Long employeeId);
}
