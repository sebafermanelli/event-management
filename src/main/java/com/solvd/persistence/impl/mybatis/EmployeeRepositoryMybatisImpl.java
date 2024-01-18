package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Employee;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class EmployeeRepositoryMybatisImpl implements EmployeeRepository {
    @Override
    public void save(Employee employee) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.save(employee);
        }
    }

    @Override
    public Collection<Employee> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findAll();
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findById(id);
        }
    }

    @Override
    public Collection<Employee> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Employee> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.deleteById(id);
        }
    }

    @Override
    public void updateById(Employee employee, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            employeeRepository.updateById(employee, id);
        }
    }

    @Override
    public Collection<Employee> findManyByEventId(Long eventId) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            EmployeeRepository employeeRepository = sqlSession.getMapper(EmployeeRepository.class);
            return employeeRepository.findManyByEventId(eventId);
        }
    }
}
