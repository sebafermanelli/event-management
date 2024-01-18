package com.solvd.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.domain.builder.EventBuilder;
import com.solvd.listener.ListenersHolder;
import com.solvd.parser.JsonDateAdapter;
import com.solvd.parser.XmlDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event extends BaseEntity {
    private String name;
    private String theme;
    private BigDecimal baseTicketPrice;

    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    @JsonDeserialize(using = JsonDateAdapter.class)
    private Date startDate;

    @XmlJavaTypeAdapter(XmlDateAdapter.class)
    @JsonDeserialize(using = JsonDateAdapter.class)
    private Date endDate;

    private String address;
    private String description;

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    private List<Employee> employees;

    public Event() {
        this.employees = new ArrayList<>();
    }

    public static EventBuilder builder() {
        return new EventBuilder();
    }

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

    public BigDecimal getBaseTicketPrice() {
        return baseTicketPrice;
    }

    public void setBaseTicketPrice(BigDecimal baseTicketPrice) {
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

    public void addEmployee(Employee employee) {
        ListenersHolder.onNewEmployee(employee, this);
        employees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        ListenersHolder.onEmployeeDismissal(employee, this);
        employees.remove(employee);
    }
}
