package com.solvd.service.impl;

import com.solvd.domain.Presenter;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.PresenterDAOJdbcImpl;
import com.solvd.service.PresenterService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public class PresenterServiceJdbcImpl implements PresenterService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final PresenterDAOJdbcImpl presenterDAOJdbc = new PresenterDAOJdbcImpl(connection);

    public PresenterServiceJdbcImpl() {
    }

    @Override
    public void create(Presenter presenter) {
        presenterDAOJdbc.save(presenter);
    }

    @Override
    public Collection<Presenter> getAll() {
        return presenterDAOJdbc.findAll();
    }

    @Override
    public Optional<Presenter> getById(Long id) {
        return presenterDAOJdbc.findById(id);
    }

    @Override
    public Collection<Presenter> getManyByColumn(String key, String value) {
        return presenterDAOJdbc.findManyByColumn(key, value);
    }

    @Override
    public Optional<Presenter> getOneByColumn(String key, String value) {
        return presenterDAOJdbc.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        presenterDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Presenter presenter, Long id) {
        presenterDAOJdbc.updateById(presenter, id);
    }
}
