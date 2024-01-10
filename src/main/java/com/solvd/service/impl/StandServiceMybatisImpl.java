package com.solvd.service.impl;

import com.solvd.domain.Stand;
import com.solvd.persistence.impl.StandDAOMybatisImpl;
import com.solvd.service.StandService;

import java.util.Collection;
import java.util.Optional;

public class StandServiceMybatisImpl implements StandService {
    private static final StandDAOMybatisImpl standDAOMybatis = new StandDAOMybatisImpl();

    @Override
    public void create(Stand stand) {
        standDAOMybatis.save(stand);
    }

    @Override
    public Collection<Stand> getAll() {
        return standDAOMybatis.findAll();
    }

    @Override
    public Optional<Stand> getById(Long id) {
        return standDAOMybatis.findById(id);
    }

    @Override
    public Collection<Stand> getManyByColumn(String key, String value) {
        return standDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Stand> getOneByColumn(String key, String value) {
        return standDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        standDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Stand stand, Long id) {
        standDAOMybatis.updateById(stand, id);
    }

    @Override
    public void addClient(Long standId, Long clientId) {
        standDAOMybatis.addClient(standId, clientId);
    }

    @Override
    public void removeClient(Long standId) {
        standDAOMybatis.removeClient(standId);
    }
}
