package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Client;
import com.solvd.persistence.ClientRepository;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class ClientRepositoryMybatisImpl implements ClientRepository {
    @Override
    public void save(Client client) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.save(client);
        }
    }

    @Override
    public Collection<Client> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);

            return clientRepository.findAll();
        }
    }

    @Override
    public Optional<Client> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findById(id);
        }
    }

    @Override
    public Collection<Client> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Client> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            return clientRepository.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.deleteById(id);
        }
    }

    @Override
    public void updateById(Client client, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            ClientRepository clientRepository = sqlSession.getMapper(ClientRepository.class);
            clientRepository.updateById(client, id);
        }
    }
}
