package com.solvd.domain;

import java.sql.Date;
import java.util.List;

public class Event extends BaseEntity {
    private String name;
    private String theme;
    private Long baseTicketPrice;
    private Date startDate;
    private Date endDate;
    private String address;
    private String description;
    private List<Room> rooms;
    private List<Ticket> tickets;
    private List<EventEmployee> employees;

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

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<EventEmployee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EventEmployee> employees) {
        this.employees = employees;
    }
}
