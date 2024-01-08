package com.solvd.persistence.impl;

import com.solvd.domain.Presentation;
import com.solvd.persistence.PersistenceConfigMybatis;
import com.solvd.persistence.PresentationDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class PresentationDAOMybatisImpl implements PresentationDAO {
    @Override
    public void save(Presentation presentation) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            presentationDAO.save(presentation);
        }
    }

    @Override
    public Collection<Presentation> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            return presentationDAO.findAll();
        }
    }

    @Override
    public Optional<Presentation> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            return presentationDAO.findById(id);
        }
    }

    @Override
    public Collection<Presentation> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            return presentationDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Presentation> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            return presentationDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            presentationDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Presentation presentation, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            presentationDAO.updateById(presentation, id);
        }
    }

    @Override
    public void save(Presentation presentation, Long roomId, Long presenterId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationDAO presentationDAO = sqlSession.getMapper(PresentationDAO.class);
            presentationDAO.save(presentation, roomId, presenterId);
        }
    }
}
