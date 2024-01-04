package com.solvd.persistence.impl;

import com.solvd.domain.Presentation;
import com.solvd.domain.Room;
import com.solvd.domain.Stand;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.RoomDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class RoomDAOJdbcImpl extends AbstractDAO<Room> implements RoomDAO {

    public RoomDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Room room) {
        String sql = "Insert into room (name, surface, capacity, status, event_id) values (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setLong(2, room.getSurface());
            stmt.setLong(3, room.getCapacity());
            stmt.setString(4, room.getStatus());
            stmt.setLong(5, room.getEventId());
            stmt.executeUpdate();
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
                Room room = new Room();
                room.setId(resultSet.getLong(1));
                room.setName(resultSet.getString(2));
                room.setSurface(resultSet.getLong(3));
                room.setCapacity(resultSet.getLong(4));
                room.setStatus(resultSet.getString(5));
                room.setEventId(resultSet.getLong(6));
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<Presentation>> presentations = presentationDAOJdbc.findManyByColumn("room_id",
                        String.valueOf(resultSet.getLong(1)));
                presentations.ifPresent(presentationsCollection -> room.setPresentations((List<Presentation>) presentationsCollection));
                StandDAOJdbcImpl standDAOJdbc = new StandDAOJdbcImpl(connection);
                Optional<Collection<Stand>> stands = standDAOJdbc.findManyByColumn("room_id", String.valueOf(resultSet.getLong(1)));
                stands.ifPresent(standsCollection -> room.setStands((List<Stand>) standsCollection));
                rooms.add(room);
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
                Room room = new Room();
                room.setId(resultSet.getLong(1));
                room.setName(resultSet.getString(2));
                room.setSurface(resultSet.getLong(3));
                room.setCapacity(resultSet.getLong(4));
                room.setStatus(resultSet.getString(5));
                room.setEventId(resultSet.getLong(6));
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<Presentation>> presentations = presentationDAOJdbc.findManyByColumn("room_id",
                        String.valueOf(resultSet.getLong(1)));
                presentations.ifPresent(presentationsCollection -> room.setPresentations((List<Presentation>) presentationsCollection));
                StandDAOJdbcImpl standDAOJdbc = new StandDAOJdbcImpl(connection);
                Optional<Collection<Stand>> stands = standDAOJdbc.findManyByColumn("room_id", String.valueOf(resultSet.getLong(1)));
                stands.ifPresent(standsCollection -> room.setStands((List<Stand>) standsCollection));
                return Optional.of(room);
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
    public Optional<Collection<Room>> findManyByColumn(String key, String value) {
        String sql = "Select * from room where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Room> rooms = new ArrayList<>();
            while (resultSet.next()) {
                Room room = new Room();
                room.setId(resultSet.getLong(1));
                room.setName(resultSet.getString(2));
                room.setSurface(resultSet.getLong(3));
                room.setCapacity(resultSet.getLong(4));
                room.setStatus(resultSet.getString(5));
                room.setEventId(resultSet.getLong(6));
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<Presentation>> presentations = presentationDAOJdbc.findManyByColumn("room_id",
                        String.valueOf(resultSet.getLong(1)));
                presentations.ifPresent(presentationsCollection -> room.setPresentations((List<Presentation>) presentationsCollection));
                StandDAOJdbcImpl standDAOJdbc = new StandDAOJdbcImpl(connection);
                Optional<Collection<Stand>> stands = standDAOJdbc.findManyByColumn("room_id", String.valueOf(resultSet.getLong(1)));
                stands.ifPresent(standsCollection -> room.setStands((List<Stand>) standsCollection));
                rooms.add(room);
            }
            return Optional.of(rooms);
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
                Room room = new Room();
                room.setId(resultSet.getLong(1));
                room.setName(resultSet.getString(2));
                room.setSurface(resultSet.getLong(3));
                room.setCapacity(resultSet.getLong(4));
                room.setStatus(resultSet.getString(5));
                room.setEventId(resultSet.getLong(6));
                PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
                Optional<Collection<Presentation>> presentations = presentationDAOJdbc.findManyByColumn("room_id",
                        String.valueOf(resultSet.getLong(1)));
                presentations.ifPresent(presentationsCollection -> room.setPresentations((List<Presentation>) presentationsCollection));
                StandDAOJdbcImpl standDAOJdbc = new StandDAOJdbcImpl(connection);
                Optional<Collection<Stand>> stands = standDAOJdbc.findManyByColumn("room_id", String.valueOf(resultSet.getLong(1)));
                stands.ifPresent(standsCollection -> room.setStands((List<Stand>) standsCollection));
                return Optional.of(room);
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
        String sql = "Delete from room where id = ?";
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
    public void updateById(Room room, Long id) {
        String sql = "Update room set name = ?, surface = ?, capacity = ?, status = ?, event_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, room.getName());
            stmt.setLong(2, room.getSurface());
            stmt.setLong(3, room.getCapacity());
            stmt.setString(4, room.getStatus());
            stmt.setLong(5, room.getEventId());
            stmt.setLong(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
