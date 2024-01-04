package com.solvd.persistence.impl;

import com.solvd.domain.Stand;
import com.solvd.persistence.PersistenceConfigMybatis;
import com.solvd.persistence.StandDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class StandDAOMybatisImpl implements StandDAO {
    @Override
    public void save(Stand stand) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandDAO standDAO = sqlSession.getMapper(StandDAO.class);
            standDAO.save(stand);
        }
    }

    @Override
    public Collection<Stand> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandDAO standDAO = sqlSession.getMapper(StandDAO.class);
            return standDAO.findAll();
        }
    }

    @Override
    public Optional<Stand> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandDAO standDAO = sqlSession.getMapper(StandDAO.class);
            return standDAO.findById(id);
        }
    }

    @Override
    public Optional<Collection<Stand>> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandDAO standDAO = sqlSession.getMapper(StandDAO.class);
            return standDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Stand> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandDAO standDAO = sqlSession.getMapper(StandDAO.class);
            return standDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandDAO standDAO = sqlSession.getMapper(StandDAO.class);
            standDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Stand stand, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandDAO standDAO = sqlSession.getMapper(StandDAO.class);
            standDAO.updateById(stand, id);
        }
    }
}
