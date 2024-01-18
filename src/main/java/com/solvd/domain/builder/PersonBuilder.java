package com.solvd.domain.builder;

public abstract class PersonBuilder<T, U> extends BaseEntityBuilder<T, U> {
    protected String cuil;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String phone;
    protected String email;

    public T cuil(String cuil) {
        this.cuil = cuil;
        return (T) this;
    }

    public T firstName(String firstName) {
        this.firstName = firstName;
        return (T) this;
    }

    public T lastName(String lastName) {
        this.lastName = lastName;
        return (T) this;
    }

    public T address(String address) {
        this.address = address;
        return (T) this;
    }

    public T phone(String phone) {
        this.phone = phone;
        return (T) this;
    }

    public T email(String email) {
        this.email = email;
        return (T) this;
    }

    @Override
    public abstract U build();
}
