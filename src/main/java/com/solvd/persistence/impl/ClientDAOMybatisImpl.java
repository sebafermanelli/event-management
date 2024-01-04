package com.solvd.persistence.impl;

import com.solvd.domain.Client;
import com.solvd.persistence.ClientDAO;
import com.solvd.persistence.PersistenceConfigMybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class ClientDAOMybatisImpl implements ClientDAO {
    @Override
    public void save(Client client) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientDAO clientDAO = sqlSession.getMapper(ClientDAO.class);
            clientDAO.save(client);
        }
    }

    @Override
    public Collection<Client> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientDAO clientDAO = sqlSession.getMapper(ClientDAO.class);

            return clientDAO.findAll();
        }
    }

    @Override
    public Optional<Client> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientDAO clientDAO = sqlSession.getMapper(ClientDAO.class);
            return clientDAO.findById(id);
        }
    }

    @Override
    public Optional<Collection<Client>> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientDAO clientDAO = sqlSession.getMapper(ClientDAO.class);
            return clientDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Client> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientDAO clientDAO = sqlSession.getMapper(ClientDAO.class);
            return clientDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientDAO clientDAO = sqlSession.getMapper(ClientDAO.class);
            clientDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Client client, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientDAO clientDAO = sqlSession.getMapper(ClientDAO.class);
            clientDAO.updateById(client, id);
        }
    }
}
