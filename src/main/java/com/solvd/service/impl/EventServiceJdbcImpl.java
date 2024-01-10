package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.domain.Event;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.EmployeeDAOJdbcImpl;
import com.solvd.persistence.impl.EventDAOJdbcImpl;
import com.solvd.service.EventService;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EventServiceJdbcImpl implements EventService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final EventDAOJdbcImpl eventDAOJdbc = new EventDAOJdbcImpl(connection);
    private static final EmployeeDAOJdbcImpl employeeDAOJdbc = new EmployeeDAOJdbcImpl(connection);


    public EventServiceJdbcImpl() {
    }

    @Override
    public void create(Event event) {
        eventDAOJdbc.save(event);
    }

    @Override
    public Collection<Event> getAll() {
        Collection<Event> events = eventDAOJdbc.findAll();
        events.forEach(event -> {
            Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(event.getId());
            event.setEmployees((List<Employee>) employees);
        });
        return events;
    }

    @Override
    public Optional<Event> getById(Long id) {
        Optional<Event> event = eventDAOJdbc.findById(id);
        event.ifPresent(e -> {
            Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(e.getId());
            e.setEmployees((List<Employee>) employees);
        });
        return event;
    }

    @Override
    public Collection<Event> getManyByColumn(String key, String value) {
        Collection<Event> events = eventDAOJdbc.findManyByColumn(key, value);
        events.forEach(event -> {
            Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(event.getId());
            event.setEmployees((List<Employee>) employees);
        });
        return events;
    }

    @Override
    public Optional<Event> getOneByColumn(String key, String value) {
        Optional<Event> event = eventDAOJdbc.findOneByColumn(key, value);
        event.ifPresent(e -> {
            Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(e.getId());
            e.setEmployees((List<Employee>) employees);
        });
        return event;
    }

    @Override
    public void deleteById(Long id) {
        eventDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Event event, Long id) {
        eventDAOJdbc.updateById(event, id);
    }

    @Override
    public void addEmployee(Long eventId, Long employeeId) {
        eventDAOJdbc.addEmployee(eventId, employeeId);
    }

    @Override
    public void removeEmployee(Long eventId, Long employeeId) {
        eventDAOJdbc.removeEmployee(eventId, employeeId);
    }

    @Override
    public Collection<Event> getManyByEmployeeId(Long employeeId) {
        Collection<Event> events = eventDAOJdbc.findManyByEmployeeId(employeeId);
        events.forEach(event -> {
            Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(event.getId());
            event.setEmployees((List<Employee>) employees);
        });
        return events;
    }
}
