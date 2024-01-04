package com.solvd.persistence;

import java.sql.Connection;

public abstract class AbstractDAO<T> implements GenericDAO<T> {
    protected Connection connection;

    public AbstractDAO(Connection connection) {
        this.connection = connection;
    }
}
