package com.solvd.service;

import com.solvd.domain.Employee;

import java.util.Collection;

public interface EmployeeService extends GenericService<Employee> {
    Collection<Employee> getManyByEventId(Long eventId);
}

