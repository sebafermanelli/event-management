package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Ticket;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import com.solvd.persistence.TicketRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class TicketRepositoryMybatisImpl implements TicketRepository {
    @Override
    public void save(Ticket ticket) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            ticketDAO.save(ticket);
        }
    }

    @Override
    public Collection<Ticket> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            return ticketDAO.findAll();
        }
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            return ticketDAO.findById(id);
        }
    }

    @Override
    public Collection<Ticket> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            return ticketDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Ticket> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            return ticketDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            ticketDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Ticket ticket, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            ticketDAO.updateById(ticket, id);
        }
    }

    @Override
    public void addPresentation(Long ticketId, Long presentationId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            ticketDAO.addPresentation(ticketId, presentationId);
        }
    }

    @Override
    public void removePresentation(Long ticketId, Long presentationId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            ticketDAO.removePresentation(ticketId, presentationId);
        }
    }

    @Override
    public Collection<Ticket> findManyByPresentationId(Long presentationId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            TicketRepository ticketDAO = sqlSession.getMapper(TicketRepository.class);
            return ticketDAO.findManyByPresentationId(presentationId);
        }
    }
}
