package com.solvd.service.impl;

import com.solvd.domain.Event;
import com.solvd.domain.Room;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.EventDAOJdbcImpl;
import com.solvd.persistence.impl.RoomDAOJdbcImpl;
import com.solvd.service.RoomService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public class RoomServiceJdbcImpl implements RoomService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final RoomDAOJdbcImpl roomDAOJdbc = new RoomDAOJdbcImpl(connection);
    private static final EventDAOJdbcImpl eventDAOJdbc = new EventDAOJdbcImpl(connection);

    public RoomServiceJdbcImpl() {
    }

    @Override
    public void create(Room room) {
        roomDAOJdbc.save(room);
    }

    @Override
    public Collection<Room> getAll() {
        Collection<Room> rooms = roomDAOJdbc.findAll();
        rooms.forEach(room -> {
            Optional<Event> event = eventDAOJdbc.findById(room.getEvent().getId());
            event.ifPresent(room::setEvent);
        });
        return rooms;
    }

    @Override
    public Optional<Room> getById(Long id) {
        Optional<Room> room = roomDAOJdbc.findById(id);
        room.ifPresent(r -> {
            Optional<Event> event = eventDAOJdbc.findById(r.getEvent().getId());
            event.ifPresent(r::setEvent);
        });
        return room;
    }

    @Override
    public Collection<Room> getManyByColumn(String key, String value) {
        Collection<Room> rooms = roomDAOJdbc.findManyByColumn(key, value);
        rooms.forEach(room -> {
            Optional<Event> event = eventDAOJdbc.findById(room.getEvent().getId());
            event.ifPresent(room::setEvent);
        });
        return rooms;
    }

    @Override
    public Optional<Room> getOneByColumn(String key, String value) {
        Optional<Room> room = roomDAOJdbc.findOneByColumn(key, value);
        room.ifPresent(r -> {
            Optional<Event> event = eventDAOJdbc.findById(r.getEvent().getId());
            event.ifPresent(r::setEvent);
        });
        return room;
    }

    @Override
    public void deleteById(Long id) {
        roomDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Room room, Long id) {
        roomDAOJdbc.updateById(room, id);
    }
}
