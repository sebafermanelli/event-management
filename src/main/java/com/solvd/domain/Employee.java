package com.solvd.domain;

import java.util.List;

public class Employee extends Person {
    private Long salary;
    private List<EventEmployee> events;

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public List<EventEmployee> getEvents() {
        return events;
    }

    public void setEvents(List<EventEmployee> events) {
        this.events = events;
    }
}
