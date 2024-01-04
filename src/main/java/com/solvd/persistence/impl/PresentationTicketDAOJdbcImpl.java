package com.solvd.persistence.impl;

import com.solvd.domain.PresentationTicket;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.PresentationTicketDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class PresentationTicketDAOJdbcImpl extends AbstractDAO<PresentationTicket> implements PresentationTicketDAO {

    public PresentationTicketDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(PresentationTicket presentationTicket) {
        String sql = "Insert into presentation_ticket (presentation_id, ticket_id) values (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, presentationTicket.getPresentationId());
            stmt.setLong(2, presentationTicket.getTicketId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<PresentationTicket> findAll() {
        String sql = "Select * from presentation_ticket";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<PresentationTicket> presentationTickets = new ArrayList<>();
            while (resultSet.next()) {
                PresentationTicket presentationTicket = new PresentationTicket();
                presentationTicket.setId(resultSet.getLong(1));
                presentationTicket.setPresentationId(resultSet.getLong(2));
                presentationTicket.setTicketId(resultSet.getLong(3));
                presentationTickets.add(presentationTicket);
            }
            return presentationTickets;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<PresentationTicket> findById(Long id) {
        String sql = "Select * from presentation_ticket where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                PresentationTicket presentationTicket = new PresentationTicket();
                presentationTicket.setId(resultSet.getLong(1));
                presentationTicket.setPresentationId(resultSet.getLong(2));
                presentationTicket.setTicketId(resultSet.getLong(3));
                return Optional.of(presentationTicket);
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
    public Optional<Collection<PresentationTicket>> findManyByColumn(String key, String value) {
        String sql = "Select * from presentation_ticket where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<PresentationTicket> presentationTickets = new ArrayList<>();
            while (resultSet.next()) {
                PresentationTicket presentationTicket = new PresentationTicket();
                presentationTicket.setId(resultSet.getLong(1));
                presentationTicket.setPresentationId(resultSet.getLong(2));
                presentationTicket.setTicketId(resultSet.getLong(3));
                presentationTickets.add(presentationTicket);
            }
            return Optional.of(presentationTickets);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<PresentationTicket> findOneByColumn(String key, String value) {
        String sql = "Select * from presentation_ticket where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                PresentationTicket presentationTicket = new PresentationTicket();
                presentationTicket.setId(resultSet.getLong(1));
                presentationTicket.setPresentationId(resultSet.getLong(2));
                presentationTicket.setTicketId(resultSet.getLong(3));
                return Optional.of(presentationTicket);
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
        String sql = "Delete from presentation_ticket where id = ?";
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
    public void updateById(PresentationTicket presentationTicket, Long id) {
        String sql = "Update presentation_ticket set presentation_id = ?, ticket_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, presentationTicket.getPresentationId());
            stmt.setLong(2, presentationTicket.getTicketId());
            stmt.setLong(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
