package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.Employee;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.service.EmployeeService;

import java.util.Collection;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(String dbType, String type) {
        this.employeeRepository = AbstractRepositoryFactory.createFactory(dbType).createEmployeeRepository(type);
    }

    @Override
    public void create(Employee employee) {
        Optional<Employee> optionalEmployee = this.getById(employee.getId());
        if (optionalEmployee.isPresent()) {
            throw new EntityAlreadyExistException("The employee with the id " + employee.getId() + "already exist");
        }
        employeeRepository.save(employee);
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Collection<Employee> getManyByColumn(String key, String value) {
        return employeeRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Employee> getOneByColumn(String key, String value) {
        return employeeRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateById(Employee employee, Long id) {
        employeeRepository.updateById(employee, id);
    }

    @Override
    public Collection<Employee> getManyByEventId(Long eventId) {
        return employeeRepository.findManyByEventId(eventId);
    }
}
