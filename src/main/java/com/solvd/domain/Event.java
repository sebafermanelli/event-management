package com.solvd.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.solvd.parser.JsonLocalDateAdapter;
import com.solvd.parser.XmlLocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "event")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event extends BaseEntity {
    private String name;
    private String theme;
    private Long baseTicketPrice;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonDeserialize(using = JsonLocalDateAdapter.class)
    private LocalDate startDate;
    @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
    @JsonDeserialize(using = JsonLocalDateAdapter.class)
    private LocalDate endDate;
    private String address;
    private String description;
    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
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
