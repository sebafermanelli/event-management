package com.solvd.domain;

import com.solvd.domain.builder.PresenterBuilder;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "presenter")
@XmlAccessorType(XmlAccessType.FIELD)
public class Presenter extends Person {
    private String specialization;

    public static PresenterBuilder builder() {
        return new PresenterBuilder();
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
