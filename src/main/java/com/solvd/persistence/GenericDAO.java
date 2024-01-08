package com.solvd.persistence;

import java.util.Collection;
import java.util.Optional;

public interface GenericDAO<T> {
    void save(T entity);

    Collection<T> findAll();

    Optional<T> findById(Long id);

    Collection<T> findManyByColumn(String key, String value);

    Optional<T> findOneByColumn(String key, String value);

    void deleteById(Long id);

    void updateById(T entity, Long id);
}
