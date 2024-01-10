package com.solvd.service.impl;

import com.solvd.domain.Event;
import com.solvd.persistence.impl.EventDAOMybatisImpl;
import com.solvd.service.EventService;

import java.util.Collection;
import java.util.Optional;

public class EventServiceMybatisImpl implements EventService {
    private static final EventDAOMybatisImpl eventDAOMybatis = new EventDAOMybatisImpl();

    @Override
    public void create(Event event) {
        eventDAOMybatis.save(event);
    }

    @Override
    public Collection<Event> getAll() {
        return eventDAOMybatis.findAll();
    }

    @Override
    public Optional<Event> getById(Long id) {
        return eventDAOMybatis.findById(id);
    }

    @Override
    public Collection<Event> getManyByColumn(String key, String value) {
        return eventDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Event> getOneByColumn(String key, String value) {
        return eventDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        eventDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Event event, Long id) {
        eventDAOMybatis.updateById(event, id);
    }

    @Override
    public void addEmployee(Long eventId, Long employeeId) {
        eventDAOMybatis.addEmployee(eventId, employeeId);
    }

    @Override
    public void removeEmployee(Long eventId, Long employeeId) {
        eventDAOMybatis.removeEmployee(eventId, employeeId);
    }

    @Override
    public Collection<Event> getManyByEmployeeId(Long employeeId) {
        return eventDAOMybatis.findManyByEmployeeId(employeeId);
    }
}
