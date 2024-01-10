package com.solvd.service.impl;

import com.solvd.domain.Presenter;
import com.solvd.persistence.impl.PresenterDAOMybatisImpl;
import com.solvd.service.PresenterService;

import java.util.Collection;
import java.util.Optional;

public class PresenterServiceMybatisImpl implements PresenterService {
    private static final PresenterDAOMybatisImpl presenterDAOMybatis = new PresenterDAOMybatisImpl();

    @Override
    public void create(Presenter presenter) {
        presenterDAOMybatis.save(presenter);
    }

    @Override
    public Collection<Presenter> getAll() {
        return presenterDAOMybatis.findAll();
    }

    @Override
    public Optional<Presenter> getById(Long id) {
        return presenterDAOMybatis.findById(id);
    }

    @Override
    public Collection<Presenter> getManyByColumn(String key, String value) {
        return presenterDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Presenter> getOneByColumn(String key, String value) {
        return presenterDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        presenterDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Presenter presenter, Long id) {
        presenterDAOMybatis.updateById(presenter, id);
    }
}
