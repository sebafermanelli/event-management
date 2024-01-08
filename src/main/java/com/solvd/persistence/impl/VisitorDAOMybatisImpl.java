package com.solvd.persistence.impl;

import com.solvd.domain.Visitor;
import com.solvd.persistence.PersistenceConfigMybatis;
import com.solvd.persistence.VisitorDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class VisitorDAOMybatisImpl implements VisitorDAO {
    @Override
    public void save(Visitor visitor) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorDAO visitorDAO = sqlSession.getMapper(VisitorDAO.class);
            visitorDAO.save(visitor);
        }
    }

    @Override
    public Collection<Visitor> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorDAO visitorDAO = sqlSession.getMapper(VisitorDAO.class);
            return visitorDAO.findAll();
        }
    }

    @Override
    public Optional<Visitor> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorDAO visitorDAO = sqlSession.getMapper(VisitorDAO.class);
            return visitorDAO.findById(id);
        }
    }

    @Override
    public Collection<Visitor> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorDAO visitorDAO = sqlSession.getMapper(VisitorDAO.class);
            return visitorDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Visitor> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorDAO visitorDAO = sqlSession.getMapper(VisitorDAO.class);
            return visitorDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorDAO visitorDAO = sqlSession.getMapper(VisitorDAO.class);
            visitorDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Visitor visitor, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            VisitorDAO visitorDAO = sqlSession.getMapper(VisitorDAO.class);
            visitorDAO.updateById(visitor, id);
        }
    }
}
