package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Event;
import com.solvd.persistence.EventRepository;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class EventRepositoryMybatisImpl implements EventRepository {
    @Override
    public void save(Event event) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            eventRepository.save(event);
        }
    }

    @Override
    public Collection<Event> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            return eventRepository.findAll();
        }
    }

    @Override
    public Optional<Event> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            return eventRepository.findById(id);
        }
    }

    @Override
    public Collection<Event> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            return eventRepository.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Event> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            return eventRepository.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            eventRepository.deleteById(id);
        }
    }

    @Override
    public void updateById(Event event, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            eventRepository.updateById(event, id);
        }
    }

    @Override
    public void addEmployee(Long eventId, Long employeeId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            eventRepository.addEmployee(eventId, employeeId);
        }
    }

    @Override
    public void removeEmployee(Long eventId, Long employeeId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            eventRepository.removeEmployee(eventId, employeeId);
        }
    }

    @Override
    public Collection<Event> findManyByEmployeeId(Long employeeId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventRepository eventRepository = sqlSession.getMapper(EventRepository.class);
            return eventRepository.findManyByEmployeeId(employeeId);
        }
    }
}
