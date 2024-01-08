package com.solvd.persistence.impl;

import com.solvd.domain.EventEmployee;
import com.solvd.persistence.EventEmployeeDAO;
import com.solvd.persistence.PersistenceConfigMybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class EventEmployeeDAOMybatisImpl implements EventEmployeeDAO {
    @Override
    public void save(EventEmployee eventEmployee) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventEmployeeDAO eventEmployeeDAO = sqlSession.getMapper(EventEmployeeDAO.class);
            eventEmployeeDAO.save(eventEmployee);
        }
    }

    @Override
    public Collection<EventEmployee> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventEmployeeDAO eventEmployeeDAO = sqlSession.getMapper(EventEmployeeDAO.class);
            return eventEmployeeDAO.findAll();
        }
    }

    @Override
    public Optional<EventEmployee> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventEmployeeDAO eventEmployeeDAO = sqlSession.getMapper(EventEmployeeDAO.class);
            return eventEmployeeDAO.findById(id);
        }
    }

    @Override
    public Collection<EventEmployee> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventEmployeeDAO eventEmployeeDAO = sqlSession.getMapper(EventEmployeeDAO.class);
            return eventEmployeeDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<EventEmployee> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventEmployeeDAO eventEmployeeDAO = sqlSession.getMapper(EventEmployeeDAO.class);
            return eventEmployeeDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventEmployeeDAO eventEmployeeDAO = sqlSession.getMapper(EventEmployeeDAO.class);
            eventEmployeeDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(EventEmployee eventEmployee, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EventEmployeeDAO eventEmployeeDAO = sqlSession.getMapper(EventEmployeeDAO.class);
            eventEmployeeDAO.updateById(eventEmployee, id);
        }
    }
}
