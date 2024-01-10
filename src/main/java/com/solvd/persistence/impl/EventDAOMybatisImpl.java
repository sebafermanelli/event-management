package com.solvd.persistence.impl;

import com.solvd.domain.Event;
import com.solvd.persistence.EventDAO;
import com.solvd.persistence.PersistenceConfigMybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class EventDAOMybatisImpl implements EventDAO {
    @Override
    public void save(Event event) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            eventDAO.save(event);
        }
    }

    @Override
    public Collection<Event> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            return eventDAO.findAll();
        }
    }

    @Override
    public Optional<Event> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            return eventDAO.findById(id);
        }
    }

    @Override
    public Collection<Event> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            return eventDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Event> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            return eventDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            eventDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Event event, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            eventDAO.updateById(event, id);
        }
    }

    @Override
    public void addEmployee(Long eventId, Long employeeId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            eventDAO.addEmployee(eventId, employeeId);
        }
    }

    @Override
    public void removeEmployee(Long eventId, Long employeeId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            eventDAO.removeEmployee(eventId, employeeId);
        }
    }

    @Override
    public Collection<Event> findManyByEmployeeId(Long employeeId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventDAO eventDAO = sqlSession.getMapper(EventDAO.class);
            return eventDAO.findManyByEmployeeId(employeeId);
        }
    }
}
