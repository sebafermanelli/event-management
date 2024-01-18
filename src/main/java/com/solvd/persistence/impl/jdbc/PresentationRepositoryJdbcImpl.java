package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Presentation;
import com.solvd.domain.Presenter;
import com.solvd.domain.Room;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.PresentationRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class PresentationRepositoryJdbcImpl extends AbstractRepository<Presentation> implements PresentationRepository {

    private final RoomRepositoryJdbcImpl roomDAOJdbc = new RoomRepositoryJdbcImpl();
    private final PresenterRepositoryJdbcImpl presenterDAOJdbc = new PresenterRepositoryJdbcImpl();

    @Override
    public void save(Presentation presentation) {
        String sql = "Insert into presentation (name, description, start_datetime, end_datetime, ticket_price, room_id, presenter_id) values (?, ?," +
                " ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, presentation.getName());
            stmt.setString(2, presentation.getDescription());
            stmt.setDate(3, new java.sql.Date(presentation.getStartDateTime().getTime()));
            stmt.setDate(4, new java.sql.Date(presentation.getEndDateTime().getTime()));
            stmt.setBigDecimal(5, presentation.getTicketPrice());
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
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(7));
                Optional<Presenter> presenter = presenterDAOJdbc.findById(resultSet.getLong(8));
                if (room.isPresent() && presenter.isPresent()) {
                    Presentation presentation = Presentation.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .description(resultSet.getString(3))
                            .startDateTime(resultSet.getDate(4))
                            .endDateTime(resultSet.getDate(5))
                            .ticketPrice(resultSet.getBigDecimal(6))
                            .room(room.get())
                            .presenter(presenter.get())
                            .build();
                    presentations.add(presentation);
                }
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
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(7));
                Optional<Presenter> presenter = presenterDAOJdbc.findById(resultSet.getLong(8));
                if (room.isPresent() && presenter.isPresent()) {
                    Presentation presentation = Presentation.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .description(resultSet.getString(3))
                            .startDateTime(resultSet.getDate(4))
                            .endDateTime(resultSet.getDate(5))
                            .ticketPrice(resultSet.getBigDecimal(6))
                            .room(room.get())
                            .presenter(presenter.get())
                            .build();
                    return Optional.of(presentation);
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
    public Collection<Presentation> findManyByColumn(String key, String value) {
        String sql = "Select * from presentation where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Presentation> presentations = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(7));
                Optional<Presenter> presenter = presenterDAOJdbc.findById(resultSet.getLong(8));
                if (room.isPresent() && presenter.isPresent()) {
                    Presentation presentation = Presentation.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .description(resultSet.getString(3))
                            .startDateTime(resultSet.getDate(4))
                            .endDateTime(resultSet.getDate(5))
                            .ticketPrice(resultSet.getBigDecimal(6))
                            .room(room.get())
                            .presenter(presenter.get())
                            .build();
                    presentations.add(presentation);
                }
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
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(7));
                Optional<Presenter> presenter = presenterDAOJdbc.findById(resultSet.getLong(8));
                if (room.isPresent() && presenter.isPresent()) {
                    Presentation presentation = Presentation.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .description(resultSet.getString(3))
                            .startDateTime(resultSet.getDate(4))
                            .endDateTime(resultSet.getDate(5))
                            .ticketPrice(resultSet.getBigDecimal(6))
                            .room(room.get())
                            .presenter(presenter.get())
                            .build();
                    return Optional.of(presentation);
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
            stmt.setDate(3, new java.sql.Date(presentation.getStartDateTime().getTime()));
            stmt.setDate(4, new java.sql.Date(presentation.getEndDateTime().getTime()));
            stmt.setBigDecimal(5, presentation.getTicketPrice());
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
