package com.solvd.persistence.impl;

import com.solvd.domain.Ticket;
import com.solvd.domain.Visitor;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.VisitorDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class VisitorDAOJdbcImpl extends AbstractDAO<Visitor> implements VisitorDAO {

    public VisitorDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Visitor visitor) {
        String sql = "Insert into visitor (cuil, first_name, last_name, address, phone, email) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, visitor.getCuil());
            stmt.setString(2, visitor.getFirstName());
            stmt.setString(3, visitor.getLastName());
            stmt.setString(4, visitor.getAddress());
            stmt.setString(5, visitor.getPhone());
            stmt.setString(6, visitor.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Visitor> findAll() {
        String sql = "Select * from visitor";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Visitor> visitors = new ArrayList<>();
            while (resultSet.next()) {
                Visitor visitor = new Visitor();
                visitor.setId(resultSet.getLong(1));
                visitor.setCuil(resultSet.getString(2));
                visitor.setFirstName(resultSet.getString(3));
                visitor.setLastName(resultSet.getString(4));
                visitor.setAddress(resultSet.getString(5));
                visitor.setPhone(resultSet.getString(6));
                visitor.setEmail(resultSet.getString(7));
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<Ticket>> attendeeTickets = ticketDAOJdbc.findManyByColumn("attendee_id", String.valueOf(visitor.getId()));
                attendeeTickets.ifPresent(attendeeTicketsCollection -> visitor.setAttendeeTickets((List<Ticket>) attendeeTicketsCollection));
                Optional<Collection<Ticket>> buyerTickets = ticketDAOJdbc.findManyByColumn("buyer_id", String.valueOf(visitor.getId()));
                buyerTickets.ifPresent(buyerTicketsCollection -> visitor.setBuyerTickets((List<Ticket>) buyerTicketsCollection));
                visitors.add(visitor);
            }
            return visitors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Visitor> findById(Long id) {
        String sql = "Select * from visitor where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Visitor visitor = new Visitor();
                visitor.setId(resultSet.getLong(1));
                visitor.setCuil(resultSet.getString(2));
                visitor.setFirstName(resultSet.getString(3));
                visitor.setLastName(resultSet.getString(4));
                visitor.setAddress(resultSet.getString(5));
                visitor.setPhone(resultSet.getString(6));
                visitor.setEmail(resultSet.getString(7));
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<Ticket>> attendeeTickets = ticketDAOJdbc.findManyByColumn("attendee_id", String.valueOf(visitor.getId()));
                attendeeTickets.ifPresent(attendeeTicketsCollection -> visitor.setAttendeeTickets((List<Ticket>) attendeeTicketsCollection));
                Optional<Collection<Ticket>> buyerTickets = ticketDAOJdbc.findManyByColumn("buyer_id", String.valueOf(visitor.getId()));
                buyerTickets.ifPresent(buyerTicketsCollection -> visitor.setBuyerTickets((List<Ticket>) buyerTicketsCollection));
                return Optional.of(visitor);
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
    public Optional<Collection<Visitor>> findManyByColumn(String key, String value) {
        String sql = "Select * from visitor where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Visitor> visitors = new ArrayList<>();
            while (resultSet.next()) {
                Visitor visitor = new Visitor();
                visitor.setId(resultSet.getLong(1));
                visitor.setCuil(resultSet.getString(2));
                visitor.setFirstName(resultSet.getString(3));
                visitor.setLastName(resultSet.getString(4));
                visitor.setAddress(resultSet.getString(5));
                visitor.setPhone(resultSet.getString(6));
                visitor.setEmail(resultSet.getString(7));
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<Ticket>> attendeeTickets = ticketDAOJdbc.findManyByColumn("attendee_id", String.valueOf(visitor.getId()));
                attendeeTickets.ifPresent(attendeeTicketsCollection -> visitor.setAttendeeTickets((List<Ticket>) attendeeTicketsCollection));
                Optional<Collection<Ticket>> buyerTickets = ticketDAOJdbc.findManyByColumn("buyer_id", String.valueOf(visitor.getId()));
                buyerTickets.ifPresent(buyerTicketsCollection -> visitor.setBuyerTickets((List<Ticket>) buyerTicketsCollection));
                visitors.add(visitor);
            }
            return Optional.of(visitors);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Visitor> findOneByColumn(String key, String value) {
        String sql = "Select * from visitor where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Visitor visitor = new Visitor();
                visitor.setId(resultSet.getLong(1));
                visitor.setCuil(resultSet.getString(2));
                visitor.setFirstName(resultSet.getString(3));
                visitor.setLastName(resultSet.getString(4));
                visitor.setAddress(resultSet.getString(5));
                visitor.setPhone(resultSet.getString(6));
                visitor.setEmail(resultSet.getString(7));
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<Ticket>> attendeeTickets = ticketDAOJdbc.findManyByColumn("attendee_id", String.valueOf(visitor.getId()));
                attendeeTickets.ifPresent(attendeeTicketsCollection -> visitor.setAttendeeTickets((List<Ticket>) attendeeTicketsCollection));
                Optional<Collection<Ticket>> buyerTickets = ticketDAOJdbc.findManyByColumn("buyer_id", String.valueOf(visitor.getId()));
                buyerTickets.ifPresent(buyerTicketsCollection -> visitor.setBuyerTickets((List<Ticket>) buyerTicketsCollection));
                return Optional.of(visitor);
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
        String sql = "Delete from visitor where id = ?";
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
    public void updateById(Visitor visitor, Long id) {
        String sql = "Update visitor set cuil = ?, first_name = ?, last_name = ?, address = ?, phone = ?, email = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, visitor.getCuil());
            stmt.setString(2, visitor.getFirstName());
            stmt.setString(3, visitor.getLastName());
            stmt.setString(4, visitor.getAddress());
            stmt.setString(5, visitor.getPhone());
            stmt.setString(6, visitor.getEmail());
            stmt.setLong(7, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
