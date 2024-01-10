package com.solvd.service.impl;

import com.solvd.domain.Employee;
import com.solvd.persistence.impl.EmployeeDAOMybatisImpl;
import com.solvd.service.EmployeeService;

import java.util.Collection;
import java.util.Optional;

public class EmployeeServiceMybatisImpl implements EmployeeService {
    private static final EmployeeDAOMybatisImpl employeeDAOMybatis = new EmployeeDAOMybatisImpl();

    @Override
    public void create(Employee employee) {
        employeeDAOMybatis.save(employee);
    }

    @Override
    public Collection<Employee> getAll() {
        return employeeDAOMybatis.findAll();
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return employeeDAOMybatis.findById(id);
    }

    @Override
    public Collection<Employee> getManyByColumn(String key, String value) {
        return employeeDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Employee> getOneByColumn(String key, String value) {
        return employeeDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        employeeDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Employee employee, Long id) {
        employeeDAOMybatis.updateById(employee, id);
    }

    @Override
    public Collection<Employee> getManyByEventId(Long eventId) {
        return employeeDAOMybatis.findManyByEventId(eventId);
    }
}
