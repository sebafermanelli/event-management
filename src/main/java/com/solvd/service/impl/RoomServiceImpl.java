package com.solvd.service.impl;

import com.solvd.domain.Client;
import com.solvd.domain.Event;
import com.solvd.domain.Room;
import com.solvd.exception.EntityAlreadyExistException;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.RoomRepository;
import com.solvd.persistence.factory.AbstractRepositoryFactory;
import com.solvd.service.RoomService;

import java.util.Collection;
import java.util.Optional;

public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final EventServiceImpl eventService;

    public RoomServiceImpl(String dbType, String type) {
        this.roomRepository = AbstractRepositoryFactory.createFactory(dbType).createRoomRepository(type);
        this.eventService = new EventServiceImpl(dbType, type);
    }


    @Override
    public void create(Room room) {
        Optional<Room> optionalRoom = this.getById(room.getId());
        if (optionalRoom.isPresent()) {
            throw new EntityAlreadyExistException("The room with the id " + room.getId() + "already exist");
        }
        Optional<Event> optionalEvent = eventService.getById(room.getEvent().getId());
        if (optionalEvent.isEmpty()) {
            throw new ResourceNotFoundException("The event does not exist");
        }
        roomRepository.save(room);
    }

    @Override
    public Collection<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Optional<Room> getById(Long id) {
        return roomRepository.findById(id);
    }

    @Override
    public Collection<Room> getManyByColumn(String key, String value) {
        return roomRepository.findManyByColumn(key, value);
    }

    @Override
    public Optional<Room> getOneByColumn(String key, String value) {
        return roomRepository.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public void updateById(Room room, Long id) {
        roomRepository.updateById(room, id);
    }
}
