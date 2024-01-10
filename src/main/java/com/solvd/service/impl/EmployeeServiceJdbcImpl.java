package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.EmployeeDAOJdbcImpl;
import com.solvd.service.EmployeeService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public class EmployeeServiceJdbcImpl implements EmployeeService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final EmployeeDAOJdbcImpl employeeDAOJdbc = new EmployeeDAOJdbcImpl(connection);

    public EmployeeServiceJdbcImpl() {
    }

    @Override
    public void create(Employee employee) {
        employeeDAOJdbc.save(employee);
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeDAOJdbc.findAll();
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return employeeDAOJdbc.findById(id);
    }

    @Override
    public Collection<Employee> getManyByColumn(String key, String value) {
        return employeeDAOJdbc.findManyByColumn(key, value);
    }

    @Override
    public Optional<Employee> getOneByColumn(String key, String value) {
        return employeeDAOJdbc.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        employeeDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Employee employee, Long id) {
        employeeDAOJdbc.updateById(employee, id);
    }

    @Override
    public Collection<Employee> getManyByEventId(Long eventId) {
        return employeeDAOJdbc.findManyByEventId(eventId);
    }
}
