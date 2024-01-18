package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.domain.Event;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.persistence.EventRepository;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.service.EmployeeService;
import com.solvd.service.EventService;

import java.util.Collection;
import java.util.Optional;

public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final EmployeeService employeeService;

    public EventServiceImpl(String dbType, String type) {
        this.eventRepository = AbstractRepositoryFactory.createFactory(dbType).createEventRepository(type);
        this.employeeService = new EmployeeServiceImpl(dbType, type);
    }

    @Override
    public void create(Event event) {
        Optional<Event> optionalEvent = this.getById(event.getId());
        if (optionalEvent.isPresent()) {
            throw new EntityAlreadyExistException("The event with the id " + event.getId() + "already exist");
        }
        eventRepository.save(event);
        event.getEmployees().forEach(employee -> {
            Optional<Employee> optionalEmployee = employeeService.getById(employee.getId());
            if (optionalEmployee.isEmpty()) {
                employeeService.create(employee);
            }
            this.addEmployee(event.getId(), employee.getId());
        });
    }

    @Override
    public Collection<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Collection<Event> getManyByColumn(String key, String value) {
        return eventRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Event> getOneByColumn(String key, String value) {
        return eventRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void updateById(Event event, Long id) {
        eventRepository.updateById(event, id);
    }

    @Override
    public void addEmployee(Long eventId, Long employeeId) {
        eventRepository.addEmployee(eventId, employeeId);
    }

    @Override
    public void removeEmployee(Long eventId, Long employeeId) {
        eventRepository.removeEmployee(eventId, employeeId);
    }

    @Override
    public Collection<Event> getManyByEmployeeId(Long employeeId) {
        return eventRepository.findManyByEmployeeId(employeeId);
    }
}
