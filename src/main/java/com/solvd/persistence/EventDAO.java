package com.solvd.persistence;

import com.solvd.domain.Event;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

public interface EventDAO extends GenericDAO<Event> {
    void addEmployee(@Param("eventId") Long eventId, @Param("employeeId") Long employeeId);

    void removeEmployee(@Param("eventId") Long eventId, @Param("employeeId") Long employeeId);

    Collection<Event> findManyByEmployeeId(@Param("employeeId") Long employeeId);
}
