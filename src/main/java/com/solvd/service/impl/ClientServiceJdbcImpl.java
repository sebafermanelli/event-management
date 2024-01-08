package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.Stand;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.ClientDAOJdbcImpl;
import com.solvd.persistence.impl.StandDAOJdbcImpl;
import com.solvd.service.ClientService;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ClientServiceJdbcImpl implements ClientService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ClientDAOJdbcImpl clientDAOJdbc = new ClientDAOJdbcImpl(connection);
    private static final StandDAOJdbcImpl standDAOJdbc = new StandDAOJdbcImpl(connection);

    public ClientServiceJdbcImpl() {
    }

    @Override
    public void create(Client client) {
        clientDAOJdbc.save(client);
    }

    @Override
    public Collection<Client> getAll() {
        Collection<Client> clients = clientDAOJdbc.findAll();
        clients.forEach(client -> {
            Collection<Stand> stands = standDAOJdbc.findManyByColumn("client_id", String.valueOf(client.getId()));
            client.setStands((List<Stand>) stands);
        });
        return clients;
    }

    @Override
    public Optional<Client> getById(Long id) {
        Optional<Client> client = clientDAOJdbc.findById(id);
        client.ifPresent(c -> {
            Collection<Stand> stands = standDAOJdbc.findManyByColumn("client_id", String.valueOf(c.getId()));
            c.setStands((List<Stand>) stands);
        });
        return client;
    }

    @Override
    public Collection<Client> getManyByColumn(String key, String value) {
        Collection<Client> clients = clientDAOJdbc.findManyByColumn(key, value);
        clients.forEach(client -> {
            Collection<Stand> stands = standDAOJdbc.findManyByColumn("client_id", String.valueOf(client.getId()));
            client.setStands((List<Stand>) stands);
        });
        return clients;
    }

    @Override
    public Optional<Client> getOneByColumn(String key, String value) {
        Optional<Client> client = clientDAOJdbc.findOneByColumn(key, value);
        client.ifPresent(c -> {
            Collection<Stand> stands = standDAOJdbc.findManyByColumn("client_id", String.valueOf(c.getId()));
            c.setStands((List<Stand>) stands);
        });
        return client;
    }

    @Override
    public void deleteById(Long id) {
        clientDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Client client, Long id) {
        clientDAOJdbc.updateById(client, id);
    }
}
