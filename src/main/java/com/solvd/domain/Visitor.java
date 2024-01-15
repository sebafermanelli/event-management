package com.solvd.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "visitor")
@XmlAccessorType(XmlAccessType.FIELD)
public class Visitor extends Person {
}
