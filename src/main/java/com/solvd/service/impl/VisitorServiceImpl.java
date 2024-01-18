package com.solvd.service.impl;

import com.solvd.domain.Visitor;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.persistence.VisitorRepository;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.service.VisitorService;

import java.util.Collection;
import java.util.Optional;

public class VisitorServiceImpl implements VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(String dbType, String type) {
        this.visitorRepository = AbstractRepositoryFactory.createFactory(dbType).createVisitorRepository(type);
    }

    @Override
    public void create(Visitor visitor) {
        Optional<Visitor> optionalVisitor = this.getById(visitor.getId());
        if (optionalVisitor.isPresent()) {
            throw new EntityAlreadyExistException("The visitor with the id " + visitor.getId() + "already exist");
        }
        visitorRepository.save(visitor);
    }

    @Override
    public Collection<Visitor> getAll() {
        return visitorRepository.findAll();
    }

    @Override
    public Optional<Visitor> getById(Long id) {
        return visitorRepository.findById(id);
    }

    @Override
    public Collection<Visitor> getManyByColumn(String key, String value) {
        return visitorRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Visitor> getOneByColumn(String key, String value) {
        return visitorRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        visitorRepository.deleteById(id);
    }

    @Override
    public void updateById(Visitor visitor, Long id) {
        visitorRepository.updateById(visitor, id);
    }
}
