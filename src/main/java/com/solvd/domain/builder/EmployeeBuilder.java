package com.solvd.domain.builder;

import com.solvd.domain.Employee;

import java.math.BigDecimal;

public class EmployeeBuilder extends PersonBuilder<EmployeeBuilder, Employee> {
    private BigDecimal salary;

    public EmployeeBuilder salary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public Employee build() {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setCuil(cuil);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAddress(address);
        employee.setPhone(phone);
        employee.setEmail(email);
        employee.setSalary(salary);
        return employee;
    }
}
