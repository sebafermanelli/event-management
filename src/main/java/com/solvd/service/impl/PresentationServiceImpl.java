package com.solvd.service.impl;

import com.solvd.domain.Presentation;
import com.solvd.domain.Presenter;
import com.solvd.domain.Room;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.PresentationRepository;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.service.PresentationService;

import java.util.Collection;
import java.util.Optional;

public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;
    private final RoomServiceImpl roomService;
    private final PresenterServiceImpl presenterService;

    public PresentationServiceImpl(String dbType, String type) {
        this.presentationRepository = AbstractRepositoryFactory.createFactory(dbType).createPresentationRepository(type);
        this.roomService = new RoomServiceImpl(dbType, type);
        this.presenterService = new PresenterServiceImpl(dbType, type);
    }

    @Override
    public void create(Presentation presentation) {
        Optional<Presentation> optionalPresentation = this.getById(presentation.getId());
        if (optionalPresentation.isPresent()) {
            throw new EntityAlreadyExistException("The presentation with the id " + presentation.getId() + "already " +
                    "exist");
        }
        Optional<Room> optionalRoom = roomService.getById(presentation.getRoom().getId());
        Optional<Presenter> optionalPresenter = presenterService.getById(presentation.getPresenter().getId());
        if (optionalRoom.isEmpty()) {
            throw new ResourceNotFoundException("The room does not exist");
        }
        if (optionalPresenter.isEmpty()) {
            presenterService.create(presentation.getPresenter());
        }
        presentationRepository.save(presentation);

    }

    @Override
    public Collection<Presentation> getAll() {
        return presentationRepository.findAll();
    }

    @Override
    public Optional<Presentation> getById(Long id) {
        return presentationRepository.findById(id);
    }

    @Override
    public Collection<Presentation> getManyByColumn(String key, String value) {
        return presentationRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Presentation> getOneByColumn(String key, String value) {
        return presentationRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        presentationRepository.deleteById(id);
    }

    @Override
    public void updateById(Presentation presentation, Long id) {
        presentationRepository.updateById(presentation, id);
    }

    @Override
    public Collection<Presentation> getManyByTicketId(Long ticketId) {
        return presentationRepository.findManyByTicketId(ticketId);
    }
}
