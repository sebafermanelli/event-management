package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Event;
import com.solvd.domain.Room;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.RoomRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class RoomRepositoryJdbcImpl extends AbstractRepository<Room> implements RoomRepository {

    private final EventRepositoryJdbcImpl eventDAOJdbc = new EventRepositoryJdbcImpl();

    @Override
    public void save(Room room) {
        String sql = "Insert into room (name, surface, capacity, status, event_id) values (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, room.getName());
            stmt.setBigDecimal(2, room.getSurface());
            stmt.setInt(3, room.getCapacity());
            stmt.setString(4, room.getStatus());
            stmt.setLong(5, room.getEvent().getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    room.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Room> findAll() {
        String sql = "Select * from room";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(6));
                if (event.isPresent()) {
                    Room room = Room.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .surface(resultSet.getBigDecimal(3))
                            .capacity(resultSet.getInt(4))
                            .status(resultSet.getString(5))
                            .event(event.get())
                            .build();
                    rooms.add(room);
                }
            }
            return rooms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Room> findById(Long id) {
        String sql = "Select * from room where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(6));
                if (event.isPresent()) {
                    Room room = Room.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .surface(resultSet.getBigDecimal(3))
                            .capacity(resultSet.getInt(4))
                            .status(resultSet.getString(5))
                            .event(event.get())
                            .build();
                    return Optional.of(room);
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
    public Collection<Room> findManyByColumn(String key, String value) {
        String sql = "Select * from room where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(6));
                if (event.isPresent()) {
                    Room room = Room.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .surface(resultSet.getBigDecimal(3))
                            .capacity(resultSet.getInt(4))
                            .status(resultSet.getString(5))
                            .event(event.get())
                            .build();
                    rooms.add(room);
                }
            }
            return rooms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Room> findOneByColumn(String key, String value) {
        String sql = "Select * from room where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Optional<Event> event = eventDAOJdbc.findById(resultSet.getLong(6));
                if (event.isPresent()) {
                    Room room = Room.builder()
                            .id(resultSet.getLong(1))
                            .name(resultSet.getString(2))
                            .surface(resultSet.getBigDecimal(3))
                            .capacity(resultSet.getInt(4))
                            .status(resultSet.getString(5))
                            .event(event.get())
                            .build();
                    return Optional.of(room);
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
        String sql = "Delete from room where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The room with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Room room, Long id) {
        String sql = "Update room set name = ?, surface = ?, capacity = ?, status = ?, event_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setBigDecimal(2, room.getSurface());
            stmt.setInt(3, room.getCapacity());
            stmt.setString(4, room.getStatus());
            stmt.setLong(5, room.getEvent().getId());
            stmt.setLong(6, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The room with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
