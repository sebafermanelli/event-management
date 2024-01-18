package com.solvd.listener;

import com.solvd.domain.Employee;
import com.solvd.domain.Event;

public interface EmployeeListener {
    void onNewEmployee(Employee employee, Event event);

    void onEmployeeDismissal(Employee employee, Event event);
}
