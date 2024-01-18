package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Visitor;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import com.solvd.persistence.VisitorRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class VisitorRepositoryMybatisImpl implements VisitorRepository {
    @Override
    public void save(Visitor visitor) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorRepository visitorDAO = sqlSession.getMapper(VisitorRepository.class);
            visitorDAO.save(visitor);
        }
    }

    @Override
    public Collection<Visitor> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorRepository visitorDAO = sqlSession.getMapper(VisitorRepository.class);
            return visitorDAO.findAll();
        }
    }

    @Override
    public Optional<Visitor> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorRepository visitorDAO = sqlSession.getMapper(VisitorRepository.class);
            return visitorDAO.findById(id);
        }
    }

    @Override
    public Collection<Visitor> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorRepository visitorDAO = sqlSession.getMapper(VisitorRepository.class);
            return visitorDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Visitor> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorRepository visitorDAO = sqlSession.getMapper(VisitorRepository.class);
            return visitorDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorRepository visitorDAO = sqlSession.getMapper(VisitorRepository.class);
            visitorDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Visitor visitor, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorRepository visitorDAO = sqlSession.getMapper(VisitorRepository.class);
            visitorDAO.updateById(visitor, id);
        }
    }
}
