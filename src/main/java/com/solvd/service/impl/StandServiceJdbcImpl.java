package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.Room;
import com.solvd.domain.Stand;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.ClientDAOJdbcImpl;
import com.solvd.persistence.impl.RoomDAOJdbcImpl;
import com.solvd.persistence.impl.StandDAOJdbcImpl;
import com.solvd.service.StandService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public class StandServiceJdbcImpl implements StandService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final StandDAOJdbcImpl standDAOJdbc = new StandDAOJdbcImpl(connection);
    private static final RoomDAOJdbcImpl roomDAOJdbc = new RoomDAOJdbcImpl(connection);
    private static final ClientDAOJdbcImpl clientDAOJdbc = new ClientDAOJdbcImpl(connection);

    public StandServiceJdbcImpl() {
    }

    @Override
    public void create(Stand stand) {
        standDAOJdbc.save(stand);
    }

    @Override
    public Collection<Stand> getAll() {
        Collection<Stand> stands = standDAOJdbc.findAll();
        stands.forEach(stand -> {
            Optional<Room> room = roomDAOJdbc.findById(stand.getRoom().getId());
            room.ifPresent(stand::setRoom);
            Optional<Client> client = clientDAOJdbc.findById(stand.getClient().getId());
            room.ifPresent(stand::setRoom);
        });
        return stands;
    }

    @Override
    public Optional<Stand> getById(Long id) {
        Optional<Stand> stand = standDAOJdbc.findById(id);
        stand.ifPresent(s -> {
            Optional<Room> room = roomDAOJdbc.findById(s.getRoom().getId());
            room.ifPresent(s::setRoom);
            Optional<Client> client = clientDAOJdbc.findById(s.getClient().getId());
            room.ifPresent(s::setRoom);
        });
        return stand;
    }

    @Override
    public Collection<Stand> getManyByColumn(String key, String value) {
        Collection<Stand> stands = standDAOJdbc.findManyByColumn(key, value);
        stands.forEach(stand -> {
            Optional<Room> room = roomDAOJdbc.findById(stand.getRoom().getId());
            room.ifPresent(stand::setRoom);
            Optional<Client> client = clientDAOJdbc.findById(stand.getClient().getId());
            room.ifPresent(stand::setRoom);
        });
        return stands;
    }

    @Override
    public Optional<Stand> getOneByColumn(String key, String value) {
        Optional<Stand> stand = standDAOJdbc.findOneByColumn(key, value);
        stand.ifPresent(s -> {
            Optional<Room> room = roomDAOJdbc.findById(s.getRoom().getId());
            room.ifPresent(s::setRoom);
            Optional<Client> client = clientDAOJdbc.findById(s.getClient().getId());
            room.ifPresent(s::setRoom);
        });
        return stand;
    }

    @Override
    public void deleteById(Long id) {
        standDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Stand stand, Long id) {
        standDAOJdbc.updateById(stand, id);
    }

    @Override
    public void addClient(Long standId, Long clientId) {
        standDAOJdbc.addClient(standId, clientId);
    }

    @Override
    public void removeClient(Long standId) {
        standDAOJdbc.removeClient(standId);
    }
}
