package com.solvd.persistence.impl;

import com.solvd.domain.Ticket;
import com.solvd.persistence.PersistenceConfigMybatis;
import com.solvd.persistence.TicketDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class TicketDAOMybatisImpl implements TicketDAO {
    @Override
    public void save(Ticket ticket) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            ticketDAO.save(ticket);
        }
    }

    @Override
    public Collection<Ticket> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            return ticketDAO.findAll();
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            return ticketDAO.findById(id);
        }
    }

    @Override
    public Collection<Ticket> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            return ticketDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Ticket> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            return ticketDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            ticketDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Ticket ticket, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            ticketDAO.updateById(ticket, id);
        }
    }

    @Override
    public void save(Ticket ticket, Long eventId, Long attendeeId, Long buyerId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            ticketDAO.save(ticket, eventId, attendeeId, buyerId);
        }
    }

    @Override
    public void addPresentation(Long ticketId, Long presentationId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketDAO ticketDAO = sqlSession.getMapper(TicketDAO.class);
            ticketDAO.addPresentation(ticketId, presentationId);
        }
    }
}
