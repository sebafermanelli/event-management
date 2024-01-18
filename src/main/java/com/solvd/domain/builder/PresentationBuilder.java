package com.solvd.domain.builder;

import com.solvd.domain.Presentation;
import com.solvd.domain.Presenter;
import com.solvd.domain.Room;

import java.math.BigDecimal;
import java.util.Date;

public class PresentationBuilder extends BaseEntityBuilder<PresentationBuilder, Presentation> {
    private String name;
    private String description;
    private Date startDateTime;
    private Date endDateTime;
    private BigDecimal ticketPrice;
    private Room room = new Room();
    private Presenter presenter = new Presenter();

    public PresentationBuilder name(String name) {
        this.name = name;
        return this;
    }

    public PresentationBuilder description(String description) {
        this.description = description;
        return this;
    }

    public PresentationBuilder startDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    public PresentationBuilder endDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public PresentationBuilder ticketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
        return this;
    }

    public PresentationBuilder room(Room room) {
        this.room = room;
        return this;
    }

    public PresentationBuilder presenter(Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public Presentation build() {
        Presentation presentation = new Presentation();
        presentation.setId(id);
        presentation.setName(name);
        presentation.setDescription(description);
        presentation.setStartDateTime(startDateTime);
        presentation.setEndDateTime(endDateTime);
        presentation.setTicketPrice(ticketPrice);
        presentation.setRoom(room);
        presentation.setPresenter(presenter);
        return presentation;
    }
}
