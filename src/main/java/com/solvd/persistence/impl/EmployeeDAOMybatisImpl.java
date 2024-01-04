package com.solvd.persistence.impl;

import com.solvd.domain.Employee;
import com.solvd.persistence.EmployeeDAO;
import com.solvd.persistence.PersistenceConfigMybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class EmployeeDAOMybatisImpl implements EmployeeDAO {
    @Override
    public void save(Employee employee) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeDAO employeeDAO = sqlSession.getMapper(EmployeeDAO.class);
            employeeDAO.save(employee);
        }
    }

    @Override
    public Collection<Employee> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeDAO employeeDAO = sqlSession.getMapper(EmployeeDAO.class);
            return employeeDAO.findAll();
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeDAO employeeDAO = sqlSession.getMapper(EmployeeDAO.class);
            return employeeDAO.findById(id);
        }
    }

    @Override
    public Optional<Collection<Employee>> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeDAO employeeDAO = sqlSession.getMapper(EmployeeDAO.class);
            return employeeDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Employee> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeDAO employeeDAO = sqlSession.getMapper(EmployeeDAO.class);
            return employeeDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeDAO employeeDAO = sqlSession.getMapper(EmployeeDAO.class);
            employeeDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Employee employee, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeDAO employeeDAO = sqlSession.getMapper(EmployeeDAO.class);
            employeeDAO.updateById(employee, id);
        }
    }
}
