package com.solvd.persistence;

import com.solvd.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Optional;

public interface EmployeeDAO extends GenericDAO<Employee> {
    Collection<Employee> findManyByEventId(@Param("eventId") Long eventId);
}
