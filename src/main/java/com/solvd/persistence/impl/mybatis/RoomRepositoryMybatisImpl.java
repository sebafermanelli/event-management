package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Room;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import com.solvd.persistence.RoomRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class RoomRepositoryMybatisImpl implements RoomRepository {
    @Override
    public void save(Room room) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomRepository roomDAO = sqlSession.getMapper(RoomRepository.class);
            roomDAO.save(room);
        }
    }

    @Override
    public Collection<Room> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomRepository roomDAO = sqlSession.getMapper(RoomRepository.class);
            return roomDAO.findAll();
        }
    }

    @Override
    public Optional<Room> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomRepository roomDAO = sqlSession.getMapper(RoomRepository.class);
            return roomDAO.findById(id);
        }
    }

    @Override
    public Collection<Room> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomRepository roomDAO = sqlSession.getMapper(RoomRepository.class);
            return roomDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Room> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomRepository roomDAO = sqlSession.getMapper(RoomRepository.class);
            return roomDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomRepository roomDAO = sqlSession.getMapper(RoomRepository.class);
            roomDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Room room, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            RoomRepository roomDAO = sqlSession.getMapper(RoomRepository.class);
            roomDAO.updateById(room, id);
        }
    }
}
