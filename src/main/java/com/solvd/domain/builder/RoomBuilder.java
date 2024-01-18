package com.solvd.domain.builder;

import com.solvd.domain.Event;
import com.solvd.domain.Room;

import java.math.BigDecimal;

public class RoomBuilder extends BaseEntityBuilder<RoomBuilder, Room> {
    private String name;
    private BigDecimal surface;
    private Integer capacity;
    private String status;
    private Event event = new Event();

    public RoomBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder surface(BigDecimal surface) {
        this.surface = surface;
        return this;
    }

    public RoomBuilder capacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public RoomBuilder status(String status) {
        this.status = status;
        return this;
    }

    public RoomBuilder event(Event event) {
        this.event = event;
        return this;
    }

    @Override
    public Room build() {
        Room room = new Room();
        room.setId(id);
        room.setName(name);
        room.setSurface(surface);
        room.setCapacity(capacity);
        room.setStatus(status);
        room.setEvent(event);
        return room;
    }
}
