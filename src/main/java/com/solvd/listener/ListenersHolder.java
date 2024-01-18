package com.solvd.listener;

import com.solvd.domain.Employee;
import com.solvd.domain.Event;

import java.util.ArrayList;
import java.util.List;

public class ListenersHolder {
    private static final List<EmployeeListener> listeners = new ArrayList<>();

    public static void subscribe(EmployeeListener employeeListener) {
        listeners.add(employeeListener);
    }

    public static void unsubscribe(EmployeeListener employeeListener) {
        listeners.remove(employeeListener);
    }

    public static void onNewEmployee(Employee employee, Event event) {
        for (EmployeeListener listener : listeners) {
            listener.onNewEmployee(employee, event);
        }
    }

    public static void onEmployeeDismissal(Employee employee, Event event) {
        for (EmployeeListener listener : listeners) {
            listener.onEmployeeDismissal(employee, event);
        }
    }
}
