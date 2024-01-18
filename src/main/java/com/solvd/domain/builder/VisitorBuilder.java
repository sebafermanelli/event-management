package com.solvd.domain.builder;

import com.solvd.domain.Visitor;

public class VisitorBuilder extends PersonBuilder<VisitorBuilder, Visitor> {
    @Override
    public Visitor build() {
        Visitor visitor = new Visitor();
        visitor.setId(id);
        visitor.setCuil(cuil);
        visitor.setFirstName(firstName);
        visitor.setLastName(lastName);
        visitor.setAddress(address);
        visitor.setPhone(phone);
        visitor.setEmail(email);
        return visitor;
    }
}
