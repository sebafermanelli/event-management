package com.solvd.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Event extends BaseEntity {
    private String name;
    private String theme;
    private Long baseTicketPrice;
    private Date startDate;
    private Date endDate;
    private String address;
    private String description;
    private List<Employee> employees = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Long getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public void setBaseTicketPrice(Long baseTicketPrice) {
        this.baseTicketPrice = baseTicketPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
