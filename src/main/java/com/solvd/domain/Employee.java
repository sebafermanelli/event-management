package com.solvd.domain;

import java.util.List;

public class Employee extends Person {
    private Long salary;
    private List<EventEmployee> roles;
    private List<Event> events;

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public List<EventEmployee> getRoles() {
        return roles;
    }

    public void setRoles(List<EventEmployee> roles) {
        this.roles = roles;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
