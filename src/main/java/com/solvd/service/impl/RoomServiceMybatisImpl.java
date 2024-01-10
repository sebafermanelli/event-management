package com.solvd.service.impl;

import com.solvd.domain.Room;
import com.solvd.persistence.impl.RoomDAOMybatisImpl;
import com.solvd.service.RoomService;

import java.util.Collection;
import java.util.Optional;

public class RoomServiceMybatisImpl implements RoomService {
    private static final RoomDAOMybatisImpl roomDAOMybatis = new RoomDAOMybatisImpl();

    @Override
    public void create(Room room) {
        roomDAOMybatis.save(room);
    }

    @Override
    public Collection<Room> getAll() {
        return roomDAOMybatis.findAll();
    }

    @Override
    public Optional<Room> getById(Long id) {
        return roomDAOMybatis.findById(id);
    }

    @Override
    public Collection<Room> getManyByColumn(String key, String value) {
        return roomDAOMybatis.findManyByColumn(key, value);
    }

    @Override
    public Optional<Room> getOneByColumn(String key, String value) {
        return roomDAOMybatis.findOneByColumn(key, value);
    }

    @Override
    public void deleteById(Long id) {
        roomDAOMybatis.deleteById(id);
    }

    @Override
    public void updateById(Room room, Long id) {
        roomDAOMybatis.updateById(room, id);
    }
}
