package com.solvd.persistence;

import com.solvd.domain.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface EmployeeRepository extends GenericRepository<Employee> {
    Collection<Employee> findManyByEventId(@Param("eventId") Long eventId);
}
