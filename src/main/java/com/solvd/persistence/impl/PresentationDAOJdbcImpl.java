package com.solvd.persistence.impl;

import com.solvd.domain.Presentation;
import com.solvd.domain.PresentationTicket;
import com.solvd.domain.Ticket;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.PresentationDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class PresentationDAOJdbcImpl extends AbstractDAO<Presentation> implements PresentationDAO {

    public PresentationDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Presentation presentation) {
        String sql = "Insert into presentation (name, description, start_datetime, end_datetime, ticket_price, room_id, presenter_id) values (?, ?," +
                " ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, presentation.getName());
            stmt.setString(2, presentation.getDescription());
            stmt.setDate(3, presentation.getStartDateTime());
            stmt.setDate(4, presentation.getEndDateTime());
            stmt.setLong(5, presentation.getTicketPrice());
            stmt.setLong(6, presentation.getRoomId());
            stmt.setLong(7, presentation.getPresenterId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Presentation> findAll() {
        String sql = "Select * from presentation";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Presentation> presentations = new ArrayList<>();
            while (resultSet.next()) {
                Presentation presentation = new Presentation();
                presentation.setId(resultSet.getLong(1));
                presentation.setName(resultSet.getString(2));
                presentation.setDescription(resultSet.getString(3));
                presentation.setStartDateTime(resultSet.getDate(4));
                presentation.setEndDateTime(resultSet.getDate(5));
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.setRoomId(resultSet.getLong(7));
                presentation.setPresenterId(resultSet.getLong(8));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("presentation_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Ticket> tickets = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Ticket> ticket = ticketDAOJdbc.findById(presentationTicket.getTicketId());
                                ticket.ifPresent(tickets::add);
                            });
                            presentation.setTickets(tickets);
                        });
                presentations.add(presentation);
            }
            return presentations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Presentation> findById(Long id) {
        String sql = "Select * from presentation where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Presentation presentation = new Presentation();
                presentation.setId(resultSet.getLong(1));
                presentation.setName(resultSet.getString(2));
                presentation.setDescription(resultSet.getString(3));
                presentation.setStartDateTime(resultSet.getDate(4));
                presentation.setEndDateTime(resultSet.getDate(5));
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.setRoomId(resultSet.getLong(7));
                presentation.setPresenterId(resultSet.getLong(8));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("presentation_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Ticket> tickets = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Ticket> ticket = ticketDAOJdbc.findById(presentationTicket.getTicketId());
                                ticket.ifPresent(tickets::add);
                            });
                            presentation.setTickets(tickets);
                        });
                return Optional.of(presentation);
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
    public Optional<Collection<Presentation>> findManyByColumn(String key, String value) {
        String sql = "Select * from presentation where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Presentation> presentations = new ArrayList<>();
            while (resultSet.next()) {
                Presentation presentation = new Presentation();
                presentation.setId(resultSet.getLong(1));
                presentation.setName(resultSet.getString(2));
                presentation.setDescription(resultSet.getString(3));
                presentation.setStartDateTime(resultSet.getDate(4));
                presentation.setEndDateTime(resultSet.getDate(5));
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.setRoomId(resultSet.getLong(7));
                presentation.setPresenterId(resultSet.getLong(8));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("presentation_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Ticket> tickets = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Ticket> ticket = ticketDAOJdbc.findById(presentationTicket.getTicketId());
                                ticket.ifPresent(tickets::add);
                            });
                            presentation.setTickets(tickets);
                        });
                presentations.add(presentation);
            }
            return Optional.of(presentations);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Presentation> findOneByColumn(String key, String value) {
        String sql = "Select * from presentation where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Presentation presentation = new Presentation();
                presentation.setId(resultSet.getLong(1));
                presentation.setName(resultSet.getString(2));
                presentation.setDescription(resultSet.getString(3));
                presentation.setStartDateTime(resultSet.getDate(4));
                presentation.setEndDateTime(resultSet.getDate(5));
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.setRoomId(resultSet.getLong(7));
                presentation.setPresenterId(resultSet.getLong(8));
                PresentationTicketDAOJdbcImpl presentationTicketDAOJdbc = new PresentationTicketDAOJdbcImpl(connection);
                TicketDAOJdbcImpl ticketDAOJdbc = new TicketDAOJdbcImpl(connection);
                Optional<Collection<PresentationTicket>> presentationTickets = presentationTicketDAOJdbc.findManyByColumn("presentation_id",
                        String.valueOf(resultSet.getLong(1)));
                presentationTickets
                        .ifPresent(presentationTicketsCollection -> {
                            List<Ticket> tickets = new ArrayList<>();
                            presentationTicketsCollection.forEach(presentationTicket -> {
                                Optional<Ticket> ticket = ticketDAOJdbc.findById(presentationTicket.getTicketId());
                                ticket.ifPresent(tickets::add);
                            });
                            presentation.setTickets(tickets);
                        });
                return Optional.of(presentation);
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
        String sql = "Delete from presentation where id = ?";
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
    public void updateById(Presentation presentation, Long id) {
        String sql = "Update presentation set name = ?, description = ?, start_datetime = ?, end_datetime = ?, ticket_price = ?, room_id = ?, " +
                "presenter_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, presentation.getName());
            stmt.setString(2, presentation.getDescription());
            stmt.setDate(3, presentation.getStartDateTime());
            stmt.setDate(4, presentation.getEndDateTime());
            stmt.setLong(5, presentation.getTicketPrice());
            stmt.setLong(6, presentation.getRoomId());
            stmt.setLong(7, presentation.getPresenterId());
            stmt.setLong(8, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
