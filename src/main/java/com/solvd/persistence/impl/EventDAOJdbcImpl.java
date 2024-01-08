package com.solvd.persistence.impl;

import com.solvd.domain.Event;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.EventDAO;
import com.solvd.persistence.PersistenceConfigJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class EventDAOJdbcImpl extends AbstractDAO<Event> implements EventDAO {

    public EventDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Event event) {
        String sql = "Insert into event (name, theme, base_ticket_price, start_date, end_date, address, description) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getTheme());
            stmt.setLong(3, event.getBaseTicketPrice());
            stmt.setDate(4, event.getStartDate());
            stmt.setDate(5, event.getEndDate());
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
                Event event = new Event();
                event.setId(resultSet.getLong(1));
                event.setName(resultSet.getString(2));
                event.setTheme(resultSet.getString(3));
                event.setBaseTicketPrice(resultSet.getLong(4));
                event.setStartDate(resultSet.getDate(5));
                event.setEndDate(resultSet.getDate(6));
                event.setAddress(resultSet.getString(7));
                event.setDescription(resultSet.getString(8));
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
                Event event = new Event();
                event.setId(resultSet.getLong(1));
                event.setName(resultSet.getString(2));
                event.setTheme(resultSet.getString(3));
                event.setBaseTicketPrice(resultSet.getLong(4));
                event.setStartDate(resultSet.getDate(5));
                event.setEndDate(resultSet.getDate(6));
                event.setAddress(resultSet.getString(7));
                event.setDescription(resultSet.getString(8));
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
                Event event = new Event();
                event.setId(resultSet.getLong(1));
                event.setName(resultSet.getString(2));
                event.setTheme(resultSet.getString(3));
                event.setBaseTicketPrice(resultSet.getLong(4));
                event.setStartDate(resultSet.getDate(5));
                event.setEndDate(resultSet.getDate(6));
                event.setAddress(resultSet.getString(7));
                event.setDescription(resultSet.getString(8));
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
                Event event = new Event();
                event.setId(resultSet.getLong(1));
                event.setName(resultSet.getString(2));
                event.setTheme(resultSet.getString(3));
                event.setBaseTicketPrice(resultSet.getLong(4));
                event.setStartDate(resultSet.getDate(5));
                event.setEndDate(resultSet.getDate(6));
                event.setAddress(resultSet.getString(7));
                event.setDescription(resultSet.getString(8));
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
            stmt.executeUpdate();
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
            stmt.setLong(3, event.getBaseTicketPrice());
            stmt.setDate(4, event.getStartDate());
            stmt.setDate(5, event.getEndDate());
            stmt.setString(6, event.getAddress());
            stmt.setString(7, event.getDescription());
            stmt.setLong(8, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
