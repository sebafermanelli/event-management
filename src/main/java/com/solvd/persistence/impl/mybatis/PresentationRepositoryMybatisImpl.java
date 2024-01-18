package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Presentation;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import com.solvd.persistence.PresentationRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class PresentationRepositoryMybatisImpl implements PresentationRepository {
    @Override
    public void save(Presentation presentation) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            presentationDAO.save(presentation);
        }
    }

    @Override
    public Collection<Presentation> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            return presentationDAO.findAll();
        }
    }

    @Override
    public Optional<Presentation> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            return presentationDAO.findById(id);
        }
    }

    @Override
    public Collection<Presentation> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            return presentationDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Presentation> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            return presentationDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            presentationDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Presentation presentation, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            presentationDAO.updateById(presentation, id);
        }
    }

    @Override
    public Collection<Presentation> findManyByTicketId(Long ticketId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationRepository presentationDAO = sqlSession.getMapper(PresentationRepository.class);
            return presentationDAO.findManyByTicketId(ticketId);
        }
    }
}
