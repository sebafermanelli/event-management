package com.solvd.service;

import java.util.Collection;
import java.util.Optional;

public interface GenericService<T> {
    void create(T entity);

    Collection<T> getAll();

    Optional<T> getById(Long id);

    Collection<T> getManyByColumn(String key, String value);

    Optional<T> getOneByColumn(String key, String value);

    void deleteById(Long id);

    void updateById(T entity, Long id);


}
