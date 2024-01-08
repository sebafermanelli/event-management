package com.solvd.domain;

import java.util.List;

public class Ticket extends BaseEntity {
    private Long cost;
    private List<Presentation> presentations;

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }
}
