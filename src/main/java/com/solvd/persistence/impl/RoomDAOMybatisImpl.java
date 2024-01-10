package com.solvd.persistence.impl;

import com.solvd.domain.Room;
import com.solvd.persistence.PersistenceConfigMybatis;
import com.solvd.persistence.RoomDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class RoomDAOMybatisImpl implements RoomDAO {
    @Override
    public void save(Room room) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomDAO roomDAO = sqlSession.getMapper(RoomDAO.class);
            roomDAO.save(room);
        }
    }

    @Override
    public Collection<Room> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomDAO roomDAO = sqlSession.getMapper(RoomDAO.class);
            return roomDAO.findAll();
        }
    }

    @Override
    public Optional<Room> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomDAO roomDAO = sqlSession.getMapper(RoomDAO.class);
            return roomDAO.findById(id);
        }
    }

    @Override
    public Collection<Room> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomDAO roomDAO = sqlSession.getMapper(RoomDAO.class);
            return roomDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Room> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomDAO roomDAO = sqlSession.getMapper(RoomDAO.class);
            return roomDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomDAO roomDAO = sqlSession.getMapper(RoomDAO.class);
            roomDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Room room, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomDAO roomDAO = sqlSession.getMapper(RoomDAO.class);
            roomDAO.updateById(room, id);
        }
    }
}
