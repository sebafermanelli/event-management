package com.solvd.service;

import java.util.Collection;
import java.util.Optional;

public interface GenericService<T> {
    void create(T entity);

    Optional<Collection<T>> getAll();

    Optional<T> getById(Long id);

    Optional<Collection<T>> findManyByColumn(String key, String value);

    Optional<T> findOneByColumn(String key, String value);

    void deleteById(Long id);

    void updateById(T entity, Long id);


}
