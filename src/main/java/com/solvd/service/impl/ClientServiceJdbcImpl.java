package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.ClientDAOJdbcImpl;
import com.solvd.service.ClientService;

import java.sql.Connection;
import java.util.Collection;
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

    public ClientServiceJdbcImpl() {
    }

    @Override
    public void create(Client client) {
        clientDAOJdbc.save(client);
    }

    @Override
    public Collection<Client> getAll() {
        return clientDAOJdbc.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientDAOJdbc.findById(id);
    }

    @Override
    public Collection<Client> getManyByColumn(String key, String value) {
        return clientDAOJdbc.findManyByColumn(key, value);
    }

    @Override
    public Optional<Client> getOneByColumn(String key, String value) {
        return clientDAOJdbc.findOneByColumn(key, value);
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
