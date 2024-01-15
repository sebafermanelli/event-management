package com.solvd.persistence.impl;

import com.solvd.domain.Presentation;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.PresentationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class PresentationDAOJdbcImpl extends AbstractDAO<Presentation> implements PresentationDAO {
    public PresentationDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Presentation presentation) {
        String sql = "Insert into presentation (name, description, start_datetime, end_datetime, ticket_price, room_id, presenter_id) values (?, ?," +
                " ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, presentation.getName());
            stmt.setString(2, presentation.getDescription());
            stmt.setDate(3, Date.valueOf(presentation.getStartDateTime()));
            stmt.setDate(4, Date.valueOf(presentation.getEndDateTime()));
            stmt.setLong(5, presentation.getTicketPrice());
            stmt.setLong(6, presentation.getRoom().getId());
            stmt.setLong(7, presentation.getPresenter().getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    presentation.setId(id);
                }
            }
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
                presentation.setStartDateTime(resultSet.getDate(4).toLocalDate());
                presentation.setEndDateTime(resultSet.getDate(5).toLocalDate());
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.getRoom().setId(resultSet.getLong(7));
                presentation.getPresenter().setId(resultSet.getLong(8));
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
                presentation.setStartDateTime(resultSet.getDate(4).toLocalDate());
                presentation.setEndDateTime(resultSet.getDate(5).toLocalDate());
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.getRoom().setId(resultSet.getLong(7));
                presentation.getPresenter().setId(resultSet.getLong(8));
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
    public Collection<Presentation> findManyByColumn(String key, String value) {
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
                presentation.setStartDateTime(resultSet.getDate(4).toLocalDate());
                presentation.setEndDateTime(resultSet.getDate(5).toLocalDate());
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.getRoom().setId(resultSet.getLong(7));
                presentation.getPresenter().setId(resultSet.getLong(8));
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
                presentation.setStartDateTime(resultSet.getDate(4).toLocalDate());
                presentation.setEndDateTime(resultSet.getDate(5).toLocalDate());
                presentation.setTicketPrice(resultSet.getLong(6));
                presentation.getRoom().setId(resultSet.getLong(7));
                presentation.getPresenter().setId(resultSet.getLong(8));
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
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The presentation with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Presentation presentation, Long id) {
        String sql = "Update presentation set name = ?, description = ?, start_datetime = ?, end_datetime = ?, ticket_price = ?, room_id = ?, presenter_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, presentation.getName());
            stmt.setString(2, presentation.getDescription());
            stmt.setDate(3, Date.valueOf(presentation.getStartDateTime()));
            stmt.setDate(4, Date.valueOf(presentation.getEndDateTime()));
            stmt.setLong(5, presentation.getTicketPrice());
            stmt.setLong(6, presentation.getRoom().getId());
            stmt.setLong(7, presentation.getPresenter().getId());
            stmt.setLong(8, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The presentation with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Presentation> findManyByTicketId(Long ticketId) {
        String sql = "Select * from presentation_ticket where ticket_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, ticketId);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Presentation> presentations = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Presentation> presentation = findById(resultSet.getLong(3));
                presentation.ifPresent(presentations::add);
            }
            return presentations;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
