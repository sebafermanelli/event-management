package com.solvd.persistence.impl;

import com.solvd.domain.EventEmployee;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.EventEmployeeDAO;
import com.solvd.persistence.PersistenceConfigJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class EventEmployeeDAOJdbcImpl extends AbstractDAO<EventEmployee> implements EventEmployeeDAO {


    public EventEmployeeDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(EventEmployee eventEmployee) {
        String sql = "Insert into event_employee (role, employee_id, event_id) values (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eventEmployee.getRole());
            stmt.setLong(2, eventEmployee.getEmployeeId());
            stmt.setLong(3, eventEmployee.getEventId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    eventEmployee.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<EventEmployee> findAll() {
        String sql = "Select * from event_employee";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<EventEmployee> eventEmployees = new ArrayList<>();
            while (resultSet.next()) {
                EventEmployee eventEmployee = new EventEmployee();
                eventEmployee.setId(resultSet.getLong(1));
                eventEmployee.setRole(resultSet.getString(2));
                eventEmployee.setEmployeeId(resultSet.getLong(3));
                eventEmployee.setEventId(resultSet.getLong(4));
                eventEmployees.add(eventEmployee);
            }
            return eventEmployees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<EventEmployee> findById(Long id) {
        String sql = "Select * from event_employee where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                EventEmployee eventEmployee = new EventEmployee();
                eventEmployee.setId(resultSet.getLong(1));
                eventEmployee.setRole(resultSet.getString(2));
                eventEmployee.setEmployeeId(resultSet.getLong(3));
                eventEmployee.setEventId(resultSet.getLong(4));
                return Optional.of(eventEmployee);
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
    public Collection<EventEmployee> findManyByColumn(String key, String value) {
        String sql = "Select * from event_employee where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<EventEmployee> eventEmployees = new ArrayList<>();
            while (resultSet.next()) {
                EventEmployee eventEmployee = new EventEmployee();
                eventEmployee.setId(resultSet.getLong(1));
                eventEmployee.setRole(resultSet.getString(2));
                eventEmployee.setEmployeeId(resultSet.getLong(3));
                eventEmployee.setEventId(resultSet.getLong(4));
                eventEmployees.add(eventEmployee);
            }
            return eventEmployees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<EventEmployee> findOneByColumn(String key, String value) {
        String sql = "Select * from event_employee where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                EventEmployee eventEmployee = new EventEmployee();
                eventEmployee.setId(resultSet.getLong(1));
                eventEmployee.setRole(resultSet.getString(2));
                eventEmployee.setEmployeeId(resultSet.getLong(3));
                eventEmployee.setEventId(resultSet.getLong(4));
                return Optional.of(eventEmployee);
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
        String sql = "Delete from event_employee where id = ?";
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
    public void updateById(EventEmployee eventEmployee, Long id) {
        String sql = "Update event_employee set role = ?, employee_id = ?, event_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eventEmployee.getRole());
            stmt.setLong(2, eventEmployee.getEmployeeId());
            stmt.setLong(3, eventEmployee.getEventId());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
