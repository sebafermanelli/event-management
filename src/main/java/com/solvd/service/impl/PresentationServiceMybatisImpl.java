package com.solvd.service.impl;

import com.solvd.domain.Presentation;
import com.solvd.persistence.impl.PresentationDAOMybatisImpl;
import com.solvd.service.PresentationService;

import java.util.Collection;
import java.util.Optional;

public class PresentationServiceMybatisImpl implements PresentationService {
    private static final PresentationDAOMybatisImpl presentationDAOMybatis = new PresentationDAOMybatisImpl();

    @Override
    public void create(Presentation presentation) {
        presentationDAOMybatis.save(presentation);
    }

    @Override
    public Collection<Presentation> getAll() {
        return presentationDAOMybatis.findAll();
    }

    @Override
    public Optional<Presentation> getById(Long id) {
        return presentationDAOMybatis.findById(id);
    }

    @Override
    public Collection<Presentation> getManyByColumn(String key, String value) {
        return presentationDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Presentation> getOneByColumn(String key, String value) {
        return presentationDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        presentationDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Presentation presentation, Long id) {
        presentationDAOMybatis.updateById(presentation, id);
    }

    @Override
    public Collection<Presentation> getManyByTicketId(Long ticketId) {
        return presentationDAOMybatis.findManyByTicketId(ticketId);
    }
}
