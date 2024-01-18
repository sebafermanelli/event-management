package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Event;
import com.solvd.domain.Presentation;
import com.solvd.domain.Ticket;
import com.solvd.domain.Visitor;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.TicketRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class TicketRepositoryJdbcImpl extends AbstractRepository<Ticket> implements TicketRepository {

    private final EventRepositoryJdbcImpl eventDAOJdbc = new EventRepositoryJdbcImpl();
    private final VisitorRepositoryJdbcImpl visitorDAOJdbc = new VisitorRepositoryJdbcImpl();
    private final PresentationRepositoryJdbcImpl presentationDAOJdbc = new PresentationRepositoryJdbcImpl();

    @Override
    public void save(Ticket ticket) {
        String sql = "Insert into ticket (cost, event_id, attendee_id, buyer_id) values (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setBigDecimal(1, ticket.getCost());
            stmt.setLong(2, ticket.getEvent().getId());
            stmt.setLong(3, ticket.getAttendee().getId());
            stmt.setLong(4, ticket.getBuyer().getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    ticket.setId(id);
                }
            }
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
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(3));
                Optional<Visitor> attendee = visitorDAOJdbc.findById(resultSet.getLong(4));
                Optional<Visitor> buyer = visitorDAOJdbc.findById(resultSet.getLong(5));
                Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(resultSet.getLong(1));
                if (event.isPresent() && attendee.isPresent() && buyer.isPresent()) {
                    Ticket ticket = Ticket.builder()
                            .id(resultSet.getLong(1))
                            .cost(resultSet.getBigDecimal(2))
                            .event(event.get())
                            .attendee(attendee.get())
                            .buyer(buyer.get())
                            .presentations((List<Presentation>) presentations)
                            .build();
                    tickets.add(ticket);
                }
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
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(3));
                Optional<Visitor> attendee = visitorDAOJdbc.findById(resultSet.getLong(4));
                Optional<Visitor> buyer = visitorDAOJdbc.findById(resultSet.getLong(5));
                Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(resultSet.getLong(1));
                if (event.isPresent() && attendee.isPresent() && buyer.isPresent()) {
                    Ticket ticket = Ticket.builder()
                            .id(resultSet.getLong(1))
                            .cost(resultSet.getBigDecimal(2))
                            .event(event.get())
                            .attendee(attendee.get())
                            .buyer(buyer.get())
                            .presentations((List<Presentation>) presentations)
                            .build();
                    return Optional.of(ticket);
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Ticket> findManyByColumn(String key, String value) {
        String sql = "Select * from ticket where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(3));
                Optional<Visitor> attendee = visitorDAOJdbc.findById(resultSet.getLong(4));
                Optional<Visitor> buyer = visitorDAOJdbc.findById(resultSet.getLong(5));
                Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(resultSet.getLong(1));
                if (event.isPresent() && attendee.isPresent() && buyer.isPresent()) {
                    Ticket ticket = Ticket.builder()
                            .id(resultSet.getLong(1))
                            .cost(resultSet.getBigDecimal(2))
                            .event(event.get())
                            .attendee(attendee.get())
                            .buyer(buyer.get())
                            .presentations((List<Presentation>) presentations)
                            .build();
                    tickets.add(ticket);
                }
            }
            return tickets;
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
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(3));
                Optional<Visitor> attendee = visitorDAOJdbc.findById(resultSet.getLong(4));
                Optional<Visitor> buyer = visitorDAOJdbc.findById(resultSet.getLong(5));
                Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(resultSet.getLong(1));
                if (event.isPresent() && attendee.isPresent() && buyer.isPresent()) {
                    Ticket ticket = Ticket.builder()
                            .id(resultSet.getLong(1))
                            .cost(resultSet.getBigDecimal(2))
                            .event(event.get())
                            .attendee(attendee.get())
                            .buyer(buyer.get())
                            .presentations((List<Presentation>) presentations)
                            .build();
                    return Optional.of(ticket);
                }
            }
            return Optional.empty();
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
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The ticket with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Ticket ticket, Long id) {
        String sql = "Update ticket set cost = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBigDecimal(1, ticket.getCost());
            stmt.setLong(2, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The ticket with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void addPresentation(Long ticketId, Long presentationId) {
        String sql = "Insert into presentation_ticket (ticket_id, presentation_id) values (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ticketId);
            stmt.setLong(2, presentationId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The entity with the ticket id " + ticketId + " and presentation id " + presentationId + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void removePresentation(Long ticketId, Long presentationId) {
        String sql = "Delete from presentation_ticket where ticket_id = ? and presentation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ticketId);
            stmt.setLong(2, presentationId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The entity with the ticket id " + ticketId + " and presentation id " + presentationId + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Ticket> findManyByPresentationId(Long presentationId) {
        String sql = "Select * from presentation_ticket where presentation_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, presentationId);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Ticket> ticket = findById(resultSet.getLong(2));
                ticket.ifPresent(tickets::add);
            }
            return tickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
