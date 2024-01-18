package com.solvd.domain.builder;

import com.solvd.domain.Employee;
import com.solvd.domain.Event;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventBuilder extends BaseEntityBuilder<EventBuilder, Event> {
    private String name;
    private String theme;
    private BigDecimal baseTicketPrice;
    private Date startDate;
    private Date endDate;
    private String address;
    private String description;
    private List<Employee> employees = new ArrayList<>();

    public EventBuilder name(String name) {
        this.name = name;
        return this;
    }

    public EventBuilder theme(String theme) {
        this.theme = theme;
        return this;
    }

    public EventBuilder baseTicketPrice(BigDecimal baseTicketPrice) {
        this.baseTicketPrice = baseTicketPrice;
        return this;
    }

    public EventBuilder startDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventBuilder endDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public EventBuilder address(String address) {
        this.address = address;
        return this;
    }

    public EventBuilder description(String description) {
        this.description = description;
        return this;
    }

    public EventBuilder employees(List<Employee> employees) {
        this.employees = employees;
        return this;
    }

    @Override
    public Event build() {
        Event event = new Event();
        event.setId(id);
        event.setName(name);
        event.setTheme(theme);
        event.setBaseTicketPrice(baseTicketPrice);
        event.setStartDate(startDate);
        event.setEndDate(endDate);
        event.setAddress(address);
        event.setDescription(description);
        event.setEmployees(employees);
        return event;
    }
}
