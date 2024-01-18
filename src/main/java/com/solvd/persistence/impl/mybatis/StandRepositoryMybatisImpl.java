package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Stand;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import com.solvd.persistence.StandRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class StandRepositoryMybatisImpl implements StandRepository {
    @Override
    public void save(Stand stand) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            standDAO.save(stand);
        }
    }

    @Override
    public Collection<Stand> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            return standDAO.findAll();
        }
    }

    @Override
    public Optional<Stand> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            return standDAO.findById(id);
        }
    }

    @Override
    public Collection<Stand> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            return standDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Stand> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            return standDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            standDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Stand stand, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            standDAO.updateById(stand, id);
        }
    }

    @Override
    public void addClient(Long standId, Long clientId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            standDAO.addClient(standId, clientId);
        }
    }

    @Override
    public void removeClient(Long standId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            StandRepository standDAO = sqlSession.getMapper(StandRepository.class);
            standDAO.removeClient(standId);
        }
    }
}
