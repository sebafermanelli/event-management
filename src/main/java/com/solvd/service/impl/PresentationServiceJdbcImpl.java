package com.solvd.service.impl;

import com.solvd.domain.Presentation;
import com.solvd.domain.Presenter;
import com.solvd.domain.Room;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.impl.PresentationDAOJdbcImpl;
import com.solvd.persistence.impl.PresenterDAOJdbcImpl;
import com.solvd.persistence.impl.RoomDAOJdbcImpl;
import com.solvd.service.PresentationService;

import java.sql.Connection;
import java.util.Collection;
import java.util.Optional;

public class PresentationServiceJdbcImpl implements PresentationService {
    private static final Connection connection;

    static {
        try {
            connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static final PresentationDAOJdbcImpl presentationDAOJdbc = new PresentationDAOJdbcImpl(connection);
    private static final PresenterDAOJdbcImpl presenterDAOJdbc = new PresenterDAOJdbcImpl(connection);
    private static final RoomDAOJdbcImpl roomDAOJdbc = new RoomDAOJdbcImpl(connection);

    public PresentationServiceJdbcImpl() {
    }

    @Override
    public void create(Presentation presentation) {
        presentationDAOJdbc.save(presentation);
    }

    @Override
    public Collection<Presentation> getAll() {
        Collection<Presentation> presentations = presentationDAOJdbc.findAll();
        presentations.forEach(presentation -> {
            Optional<Presenter> presenter = presenterDAOJdbc.findById(presentation.getPresenter().getId());
            presenter.ifPresent(presentation::setPresenter);
            Optional<Room> room = roomDAOJdbc.findById(presentation.getRoom().getId());
            room.ifPresent(presentation::setRoom);
        });
        return presentations;
    }

    @Override
    public Optional<Presentation> getById(Long id) {
        Optional<Presentation> presentation = presentationDAOJdbc.findById(id);
        presentation.ifPresent(p -> {
            Optional<Presenter> presenter = presenterDAOJdbc.findById(p.getPresenter().getId());
            presenter.ifPresent(p::setPresenter);
            Optional<Room> room = roomDAOJdbc.findById(p.getRoom().getId());
            room.ifPresent(p::setRoom);
        });
        return presentation;
    }

    @Override
    public Collection<Presentation> getManyByColumn(String key, String value) {
        Collection<Presentation> presentations = presentationDAOJdbc.findManyByColumn(key, value);
        presentations.forEach(presentation -> {
            Optional<Presenter> presenter = presenterDAOJdbc.findById(presentation.getPresenter().getId());
            presenter.ifPresent(presentation::setPresenter);
            Optional<Room> room = roomDAOJdbc.findById(presentation.getRoom().getId());
            room.ifPresent(presentation::setRoom);
        });
        return presentations;
    }

    @Override
    public Optional<Presentation> getOneByColumn(String key, String value) {
        Optional<Presentation> presentation = presentationDAOJdbc.findOneByColumn(key, value);
        presentation.ifPresent(p -> {
            Optional<Presenter> presenter = presenterDAOJdbc.findById(p.getPresenter().getId());
            presenter.ifPresent(p::setPresenter);
            Optional<Room> room = roomDAOJdbc.findById(p.getRoom().getId());
            room.ifPresent(p::setRoom);
        });
        return presentation;
    }

    @Override
    public void deleteById(Long id) {
        presentationDAOJdbc.deleteById(id);
    }

    @Override
    public void updateById(Presentation presentation, Long id) {
        presentationDAOJdbc.updateById(presentation, id);
    }

    @Override
    public Collection<Presentation> getManyByTicketId(Long ticketId) {
        Collection<Presentation> presentations = presentationDAOJdbc.findManyByTicketId(ticketId);
        presentations.forEach(presentation -> {
            Optional<Presenter> presenter = presenterDAOJdbc.findById(presentation.getPresenter().getId());
            presenter.ifPresent(presentation::setPresenter);
            Optional<Room> room = roomDAOJdbc.findById(presentation.getRoom().getId());
            room.ifPresent(presentation::setRoom);
        });
        return presentations;
    }
}
