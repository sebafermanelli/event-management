package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Employee;
import com.solvd.domain.Event;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.EventRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class EventRepositoryJdbcImpl extends AbstractRepository<Event> implements EventRepository {

    private final EmployeeRepositoryJdbcImpl employeeDAOJdbc = new EmployeeRepositoryJdbcImpl();

    @Override
    public void save(Event event) {
        String sql = "Insert into event (name, theme, base_ticket_price, start_date, end_date, address, description) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getTheme());
            stmt.setBigDecimal(3, event.getBaseTicketPrice());
            stmt.setDate(4, new java.sql.Date(event.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(event.getEndDate().getTime()));
            stmt.setString(6, event.getAddress());
            stmt.setString(7, event.getDescription());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    event.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Event> findAll() {
        String sql = "Select * from event";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Event> events = new ArrayList<>();
            while (resultSet.next()) {
                Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(resultSet.getLong(1));
                Event event = Event.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .theme(resultSet.getString(3))
                        .baseTicketPrice(resultSet.getBigDecimal(4))
                        .startDate(resultSet.getDate(5))
                        .endDate(resultSet.getDate(6))
                        .address(resultSet.getString(7))
                        .description(resultSet.getString(8))
                        .employees((List<Employee>) employees)
                        .build();
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Event> findById(Long id) {
        String sql = "Select * from event where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(resultSet.getLong(1));
                Event event = Event.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .theme(resultSet.getString(3))
                        .baseTicketPrice(resultSet.getBigDecimal(4))
                        .startDate(resultSet.getDate(5))
                        .endDate(resultSet.getDate(6))
                        .address(resultSet.getString(7))
                        .description(resultSet.getString(8))
                        .employees((List<Employee>) employees)
                        .build();
                return Optional.of(event);
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
    public Collection<Event> findManyByColumn(String key, String value) {
        String sql = "Select * from event where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Event> events = new ArrayList<>();
            while (resultSet.next()) {
                Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(resultSet.getLong(1));
                Event event = Event.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .theme(resultSet.getString(3))
                        .baseTicketPrice(resultSet.getBigDecimal(4))
                        .startDate(resultSet.getDate(5))
                        .endDate(resultSet.getDate(6))
                        .address(resultSet.getString(7))
                        .description(resultSet.getString(8))
                        .employees((List<Employee>) employees)
                        .build();
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Event> findOneByColumn(String key, String value) {
        String sql = "Select * from event where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Collection<Employee> employees = employeeDAOJdbc.findManyByEventId(resultSet.getLong(1));
                Event event = Event.builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .theme(resultSet.getString(3))
                        .baseTicketPrice(resultSet.getBigDecimal(4))
                        .startDate(resultSet.getDate(5))
                        .endDate(resultSet.getDate(6))
                        .address(resultSet.getString(7))
                        .description(resultSet.getString(8))
                        .employees((List<Employee>) employees)
                        .build();
                return Optional.of(event);
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
        String sql = "Delete from event where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The event with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Event event, Long id) {
        String sql = "Update event set name = ?, theme = ?, base_ticket_price = ?, start_date = ?, end_date = ?, address = ?, description = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getTheme());
            stmt.setBigDecimal(3, event.getBaseTicketPrice());
            stmt.setDate(4, new java.sql.Date(event.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(event.getEndDate().getTime()));
            stmt.setString(6, event.getAddress());
            stmt.setString(7, event.getDescription());
            stmt.setLong(8, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The event with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void addEmployee(Long eventId, Long employeeId) {
        String sql = "Insert into event_employee (employee_id, event_id) values (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, employeeId);
            stmt.setLong(2, eventId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void removeEmployee(Long eventId, Long employeeId) {
        String sql = "Delete from event_employee where employee_id  = ? and event_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, employeeId);
            stmt.setLong(2, eventId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The entity with the event id " + eventId + " and employee id " + employeeId + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Event> findManyByEmployeeId(Long employeeId) {
        String sql = "Select * from event_employee where employee_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, employeeId);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Event> events = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Event> event = findById(resultSet.getLong(3));
                event.ifPresent(events::add);
            }
            return events;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
