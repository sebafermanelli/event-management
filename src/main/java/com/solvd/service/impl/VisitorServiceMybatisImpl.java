package com.solvd.service.impl;

import com.solvd.domain.Visitor;
import com.solvd.persistence.impl.VisitorDAOMybatisImpl;
import com.solvd.service.VisitorService;

import java.util.Collection;
import java.util.Optional;

public class VisitorServiceMybatisImpl implements VisitorService {
    private static final VisitorDAOMybatisImpl visitorDAOMybatis = new VisitorDAOMybatisImpl();

    @Override
    public void create(Visitor visitor) {
        visitorDAOMybatis.save(visitor);
    }

    @Override
    public Collection<Visitor> getAll() {
        return visitorDAOMybatis.findAll();
    }

    @Override
    public Optional<Visitor> getById(Long id) {
        return visitorDAOMybatis.findById(id);
    }

    @Override
    public Collection<Visitor> getManyByColumn(String key, String value) {
        return visitorDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Visitor> getOneByColumn(String key, String value) {
        return visitorDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        visitorDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Visitor visitor, Long id) {
        visitorDAOMybatis.updateById(visitor, id);
    }
}
