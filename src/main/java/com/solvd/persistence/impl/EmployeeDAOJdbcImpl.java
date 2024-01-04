package com.solvd.persistence.impl;

import com.solvd.domain.Employee;
import com.solvd.domain.EventEmployee;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.EmployeeDAO;
import com.solvd.persistence.PersistenceConfigJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EmployeeDAOJdbcImpl extends AbstractDAO<Employee> implements EmployeeDAO {

    public EmployeeDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Employee employee) {
        String sql = "Insert into employee (cuil, first_name, last_name, address, phone, email, salary) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getCuil());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getAddress());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getEmail());
            stmt.setLong(7, employee.getSalary());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Employee> findAll() {
        String sql = "Select * from employee";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setCuil(resultSet.getString(2));
                employee.setFirstName(resultSet.getString(3));
                employee.setLastName(resultSet.getString(4));
                employee.setAddress(resultSet.getString(5));
                employee.setPhone(resultSet.getString(6));
                employee.setEmail(resultSet.getString(7));
                employee.setSalary(resultSet.getLong(8));
                EventEmployeeDAOJdbcImpl eventEmployeeDAOJdbc = new EventEmployeeDAOJdbcImpl(connection);
                Optional<Collection<EventEmployee>> events = eventEmployeeDAOJdbc.findManyByColumn("employee_id",
                        String.valueOf(resultSet.getLong(1)));
                events.ifPresent(eventsCollection -> employee.setEvents((List<EventEmployee>) eventsCollection));
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        String sql = "Select * from employee where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setCuil(resultSet.getString(2));
                employee.setFirstName(resultSet.getString(3));
                employee.setLastName(resultSet.getString(4));
                employee.setAddress(resultSet.getString(5));
                employee.setPhone(resultSet.getString(6));
                employee.setEmail(resultSet.getString(7));
                employee.setSalary(resultSet.getLong(8));
                EventEmployeeDAOJdbcImpl eventEmployeeDAOJdbc = new EventEmployeeDAOJdbcImpl(connection);
                Optional<Collection<EventEmployee>> events = eventEmployeeDAOJdbc.findManyByColumn("employee_id",
                        String.valueOf(resultSet.getLong(1)));
                events.ifPresent(eventsCollection -> employee.setEvents((List<EventEmployee>) eventsCollection));
                return Optional.of(employee);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Collection<Employee>> findManyByColumn(String key, String value) {
        String sql = "Select * from employee where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setCuil(resultSet.getString(2));
                employee.setFirstName(resultSet.getString(3));
                employee.setLastName(resultSet.getString(4));
                employee.setAddress(resultSet.getString(5));
                employee.setPhone(resultSet.getString(6));
                employee.setEmail(resultSet.getString(7));
                employee.setSalary(resultSet.getLong(8));
                EventEmployeeDAOJdbcImpl eventEmployeeDAOJdbc = new EventEmployeeDAOJdbcImpl(connection);
                Optional<Collection<EventEmployee>> events = eventEmployeeDAOJdbc.findManyByColumn("employee_id",
                        String.valueOf(resultSet.getLong(1)));
                events.ifPresent(eventsCollection -> employee.setEvents((List<EventEmployee>) eventsCollection));
                employees.add(employee);
            }
            return Optional.of(employees);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Employee> findOneByColumn(String key, String value) {
        String sql = "Select * from employee where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setCuil(resultSet.getString(2));
                employee.setFirstName(resultSet.getString(3));
                employee.setLastName(resultSet.getString(4));
                employee.setAddress(resultSet.getString(5));
                employee.setPhone(resultSet.getString(6));
                employee.setEmail(resultSet.getString(7));
                employee.setSalary(resultSet.getLong(8));
                EventEmployeeDAOJdbcImpl eventEmployeeDAOJdbc = new EventEmployeeDAOJdbcImpl(connection);
                Optional<Collection<EventEmployee>> events = eventEmployeeDAOJdbc.findManyByColumn("employee_id",
                        String.valueOf(resultSet.getLong(1)));
                events.ifPresent(eventsCollection -> employee.setEvents((List<EventEmployee>) eventsCollection));
                return Optional.of(employee);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete from employee where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Employee employee, Long id) {
        String sql = "Update employee set cuil = ?, first_name = ?, last_name = ?, address = ?, phone = ?, email = ?, salary = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getCuil());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getAddress());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getEmail());
            stmt.setLong(7, employee.getSalary());
            stmt.setLong(8, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
