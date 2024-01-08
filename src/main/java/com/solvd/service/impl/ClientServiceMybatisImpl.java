package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.persistence.impl.ClientDAOMybatisImpl;
import com.solvd.service.ClientService;

import java.util.Collection;
import java.util.Optional;

public class ClientServiceMybatisImpl implements ClientService {
    private static final ClientDAOMybatisImpl clientDAOMybatis = new ClientDAOMybatisImpl();

    @Override
    public void create(Client client) {
        clientDAOMybatis.save(client);
    }

    @Override
    public Collection<Client> getAll() {
        return clientDAOMybatis.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientDAOMybatis.findById(id);
    }

    @Override
    public Collection<Client> getManyByColumn(String key, String value) {
        return clientDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Client> getOneByColumn(String key, String value) {
        return clientDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        clientDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Client client, Long id) {
        clientDAOMybatis.updateById(client, id);
    }
}
