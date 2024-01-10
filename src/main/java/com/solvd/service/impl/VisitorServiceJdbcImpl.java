package com.solvd.service.impl;

import com.solvd.domain.Visitor;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.VisitorDAOJdbcImpl;
import com.solvd.service.VisitorService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public class VisitorServiceJdbcImpl implements VisitorService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final VisitorDAOJdbcImpl visitorDAOJdbc = new VisitorDAOJdbcImpl(connection);

    public VisitorServiceJdbcImpl() {
    }

    @Override
    public void create(Visitor visitor) {
        visitorDAOJdbc.save(visitor);
    }

    @Override
    public Collection<Visitor> getAll() {
        return visitorDAOJdbc.findAll();
    }

    @Override
    public Optional<Visitor> getById(Long id) {
        return visitorDAOJdbc.findById(id);
    }

    @Override
    public Collection<Visitor> getManyByColumn(String key, String value) {
        return visitorDAOJdbc.findManyByColumn(key, value);
    }

    @Override
    public Optional<Visitor> getOneByColumn(String key, String value) {
        return visitorDAOJdbc.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        visitorDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Visitor visitor, Long id) {
        visitorDAOJdbc.updateById(visitor, id);
    }
}
