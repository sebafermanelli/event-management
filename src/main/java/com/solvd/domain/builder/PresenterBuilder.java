package com.solvd.domain.builder;

import com.solvd.domain.Presenter;

public class PresenterBuilder extends PersonBuilder<PresenterBuilder, Presenter> {
    private String specialization;

    public PresenterBuilder specialization(String salary) {
        this.specialization = specialization;
        return this;
    }

    @Override
    public Presenter build() {
        Presenter presenter = new Presenter();
        presenter.setId(id);
        presenter.setCuil(cuil);
        presenter.setFirstName(firstName);
        presenter.setLastName(lastName);
        presenter.setAddress(address);
        presenter.setPhone(phone);
        presenter.setEmail(email);
        presenter.setSpecialization(specialization);
        return presenter;
    }
}
