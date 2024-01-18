package com.solvd.persistence;

import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.Connection;

public abstract class AbstractRepository<T> implements GenericRepository<T> {
    protected Connection connection;

    public AbstractRepository() {
        try {
            this.connection = PersistenceConfigJdbc.getConnection();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
