package com.solvd.persistence;

import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.Optional;

public interface GenericDAO<T> {
    void save(T entity);

    Collection<T> findAll();

    Optional<T> findById(@Param("id") Long id);

    Optional<Collection<T>> findManyByColumn(@Param("key") String key, @Param("value") String value);

    Optional<T> findOneByColumn(@Param("key") String key, @Param("value") String value);

    void deleteById(@Param("id") Long id);

    void updateById(T entity, @Param("id") Long id);
}
