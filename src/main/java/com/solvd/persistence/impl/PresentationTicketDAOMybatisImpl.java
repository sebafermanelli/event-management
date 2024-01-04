package com.solvd.persistence.impl;

import com.solvd.domain.PresentationTicket;
import com.solvd.persistence.PersistenceConfigMybatis;
import com.solvd.persistence.PresentationTicketDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class PresentationTicketDAOMybatisImpl implements PresentationTicketDAO {
    @Override
    public void save(PresentationTicket presentationTicket) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationTicketDAO presentationTicketDAO = sqlSession.getMapper(PresentationTicketDAO.class);
            presentationTicketDAO.save(presentationTicket);
        }
    }

    @Override
    public Collection<PresentationTicket> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationTicketDAO presentationTicketDAO = sqlSession.getMapper(PresentationTicketDAO.class);
            return presentationTicketDAO.findAll();
        }
    }

    @Override
    public Optional<PresentationTicket> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationTicketDAO presentationTicketDAO = sqlSession.getMapper(PresentationTicketDAO.class);
            return presentationTicketDAO.findById(id);
        }
    }

    @Override
    public Optional<Collection<PresentationTicket>> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationTicketDAO presentationTicketDAO = sqlSession.getMapper(PresentationTicketDAO.class);
            return presentationTicketDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<PresentationTicket> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationTicketDAO presentationTicketDAO = sqlSession.getMapper(PresentationTicketDAO.class);
            return presentationTicketDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationTicketDAO presentationTicketDAO = sqlSession.getMapper(PresentationTicketDAO.class);
            presentationTicketDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(PresentationTicket presentationTicket, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresentationTicketDAO presentationTicketDAO = sqlSession.getMapper(PresentationTicketDAO.class);
            presentationTicketDAO.updateById(presentationTicket, id);
        }
    }
}
