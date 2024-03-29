package com.solvd.persistence.impl.mybatis;

import com.solvd.domain.Presenter;
import com.solvd.persistence.config.PersistenceConfigMybatis;
import com.solvd.persistence.PresenterRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Collection;
import java.util.Optional;

public class PresenterRepositoryMybatisImpl implements PresenterRepository {
    @Override
    public void save(Presenter presenter) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresenterRepository presenterDAO = sqlSession.getMapper(PresenterRepository.class);
            presenterDAO.save(presenter);
        }
    }

    @Override
    public Collection<Presenter> findAll() {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresenterRepository presenterDAO = sqlSession.getMapper(PresenterRepository.class);
            return presenterDAO.findAll();
        }
    }

    @Override
    public Optional<Presenter> findById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresenterRepository presenterDAO = sqlSession.getMapper(PresenterRepository.class);
            return presenterDAO.findById(id);
        }
    }

    @Override
    public Collection<Presenter> findManyByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresenterRepository presenterDAO = sqlSession.getMapper(PresenterRepository.class);
            return presenterDAO.findManyByColumn(key, value);
        }
    }

    @Override
    public Optional<Presenter> findOneByColumn(String key, String value) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresenterRepository presenterDAO = sqlSession.getMapper(PresenterRepository.class);
            return presenterDAO.findOneByColumn(key, value);
        }
    }

    @Override
    public void deleteById(Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresenterRepository presenterDAO = sqlSession.getMapper(PresenterRepository.class);
            presenterDAO.deleteById(id);
        }
    }

    @Override
    public void updateById(Presenter presenter, Long id) {
        try (SqlSession sqlSession = PersistenceConfigMybatis.getSessionFactory().openSession(true)) {
            PresenterRepository presenterDAO = sqlSession.getMapper(PresenterRepository.class);
            presenterDAO.updateById(presenter, id);
        }
    }
}
