package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.Presenter;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.persistence.PresenterRepository;
import com.solvd.service.PresenterService;

import java.util.Collection;
import java.util.Optional;

public class PresenterServiceImpl implements PresenterService {
    private final PresenterRepository presenterRepository;

    public PresenterServiceImpl(String dbType, String type) {
        this.presenterRepository = AbstractRepositoryFactory.createFactory(dbType).createPresenterRepository(type);
    }

    @Override
    public void create(Presenter presenter) {
        Optional<Presenter> optionalPresenter = this.getById(presenter.getId());
        if (optionalPresenter.isPresent()) {
            throw new EntityAlreadyExistException("The presenter with the id " + presenter.getId() + "already exist");
        }
        presenterRepository.save(presenter);
    }

    @Override
    public Collection<Presenter> getAll() {
        return presenterRepository.findAll();
    }

    @Override
    public Optional<Presenter> getById(Long id) {
        return presenterRepository.findById(id);
    }

    @Override
    public Collection<Presenter> getManyByColumn(String key, String value) {
        return presenterRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Presenter> getOneByColumn(String key, String value) {
        return presenterRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        presenterRepository.deleteById(id);
    }

    @Override
    public void updateById(Presenter presenter, Long id) {
        presenterRepository.updateById(presenter, id);
    }
}
