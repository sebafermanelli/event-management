package com.solvd.service.impl;

import com.solvd.domain.Room;
import com.solvd.domain.Stand;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.StandRepository;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.service.StandService;

import java.util.Collection;
import java.util.Optional;

public class StandServiceImpl implements StandService {
    private final StandRepository standRepository;
    private final RoomServiceImpl roomService;

    public StandServiceImpl(String dbType, String type) {
        this.standRepository = AbstractRepositoryFactory.createFactory(dbType).createStandRepository(type);
        this.roomService = new RoomServiceImpl(dbType, type);
    }

    @Override
    public void create(Stand stand) {
        Optional<Stand> optionalStand = this.getById(stand.getId());
        if (optionalStand.isPresent()) {
            throw new EntityAlreadyExistException("The stand with the id " + stand.getId() + "already exist");
        }
        Optional<Room> optionalRoom = roomService.getById(stand.getRoom().getId());
        if (optionalRoom.isEmpty()) {
            throw new ResourceNotFoundException("The room does not exist");
        }
        standRepository.save(stand);
    }

    @Override
    public Collection<Stand> getAll() {
        return standRepository.findAll();
    }

    @Override
    public Optional<Stand> getById(Long id) {
        return standRepository.findById(id);
    }

    @Override
    public Collection<Stand> getManyByColumn(String key, String value) {
        return standRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Stand> getOneByColumn(String key, String value) {
        return standRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        standRepository.deleteById(id);
    }

    @Override
    public void updateById(Stand stand, Long id) {
        standRepository.updateById(stand, id);
    }

    @Override
    public void addClient(Long standId, Long clientId) {
        standRepository.addClient(standId, clientId);
    }

    @Override
    public void removeClient(Long standId) {
        standRepository.removeClient(standId);
    }
}
