package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.persistence.ClientRepository;
import com.solvd.service.ClientService;

import java.util.Collection;
import java.util.Optional;

public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(String dbType, String type) {
        this.clientRepository = AbstractRepositoryFactory.createFactory(dbType).createClientRepository(type);
    }

    @Override
    public void create(Client client) {
        Optional<Client> optionalClient = this.getById(client.getId());
        if (optionalClient.isPresent()) {
            throw new EntityAlreadyExistException("The client with the id " + client.getId() + "already exist");
        }
        clientRepository.save(client);
    }

    @Override
    public Collection<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Collection<Client> getManyByColumn(String key, String value) {
        return clientRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Client> getOneByColumn(String key, String value) {
        return clientRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void updateById(Client client, Long id) {
        clientRepository.updateById(client, id);
    }
}
