package com.solvd.persistence.impl;

import com.solvd.domain.Presentation;
import com.solvd.domain.PresentationTicket;
import com.solvd.domain.Ticket;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.TicketDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TicketDAOJdbcImpl extends AbstractDAO<Ticket> implements TicketDAO {

    public TicketDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Ticket ticket) {
        String sql = "Insert into ticket (cost, event_id, attendee_id, buyer_id) values (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ticket.getCost());
            stmt.setLong(2, ticket.getEventId());
            stmt.setLong(3, ticket.getAttendeeId());
            stmt.setLong(4, ticket.getBuyerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Ticket> findAll() {
        String sql = "Select * from ticket";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong(1));
                ticket.setCost(resultSet.getLong(2));
                ticket.setEventId(resultSet.getLong(3));
                ticket.setAttendeeId(resultSet.getLong(4));
                ticket.setBuyerId(resultSet.getLong(5));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("ticket_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Presentation> presentations = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Presentation> presentation = presentationDAOJdbc.findById(presentationTicket.getPresentationId());
                                presentation.ifPresent(presentations::add);
                            });
                            ticket.setPresentations(presentations);
                        });
                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        String sql = "Select * from ticket where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong(1));
                ticket.setCost(resultSet.getLong(2));
                ticket.setEventId(resultSet.getLong(3));
                ticket.setAttendeeId(resultSet.getLong(4));
                ticket.setBuyerId(resultSet.getLong(5));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("ticket_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Presentation> presentations = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Presentation> presentation = presentationDAOJdbc.findById(presentationTicket.getPresentationId());
                                presentation.ifPresent(presentations::add);
                            });
                            ticket.setPresentations(presentations);
                        });
                return Optional.of(ticket);
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
    public Optional<Collection<Ticket>> findManyByColumn(String key, String value) {
        String sql = "Select * from ticket where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong(1));
                ticket.setCost(resultSet.getLong(2));
                ticket.setEventId(resultSet.getLong(3));
                ticket.setAttendeeId(resultSet.getLong(4));
                ticket.setBuyerId(resultSet.getLong(5));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("ticket_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Presentation> presentations = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Presentation> presentation = presentationDAOJdbc.findById(presentationTicket.getPresentationId());
                                presentation.ifPresent(presentations::add);
                            });
                            ticket.setPresentations(presentations);
                        });
                tickets.add(ticket);
            }
            return Optional.of(tickets);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Ticket> findOneByColumn(String key, String value) {
        String sql = "Select * from ticket where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong(1));
                ticket.setCost(resultSet.getLong(2));
                ticket.setEventId(resultSet.getLong(3));
                ticket.setAttendeeId(resultSet.getLong(4));
                ticket.setBuyerId(resultSet.getLong(5));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("ticket_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Presentation> presentations = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Presentation> presentation = presentationDAOJdbc.findById(presentationTicket.getPresentationId());
                                presentation.ifPresent(presentations::add);
                            });
                            ticket.setPresentations(presentations);
                        });
                return Optional.of(ticket);
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
        String sql = "Delete from ticket where id = ?";
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
    public void updateById(Ticket ticket, Long id) {
        String sql = "Update ticket set cost = ?, event_id = ?, attendee_id = ?, buyer_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ticket.getCost());
            stmt.setLong(2, ticket.getEventId());
            stmt.setLong(3, ticket.getAttendeeId());
            stmt.setLong(4, ticket.getBuyerId());
            stmt.setLong(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
