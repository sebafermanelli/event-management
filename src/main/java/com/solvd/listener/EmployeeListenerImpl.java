package com.solvd.listener;

import com.solvd.domain.Employee;
import com.solvd.domain.Event;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmployeeListenerImpl implements EmployeeListener {
    private static final Logger LOGGER = LogManager.getLogger(EmployeeListenerImpl.class);

    @Override
    public void onNewEmployee(Employee employee, Event event) {
        LOGGER.info("Give the staff card to the employee");
    }

    @Override
    public void onEmployeeDismissal(Employee employee, Event event) {
        LOGGER.info("Remove the staff card to the employee");
    }
}
