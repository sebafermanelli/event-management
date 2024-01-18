package com.solvd.domain.builder;

public abstract class BaseEntityBuilder<T, U> {
    protected Long id;

    public T id(Long id) {
        this.id = id;
        return (T) this;
    }

    public abstract U build();
}
