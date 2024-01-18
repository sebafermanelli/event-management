package com.solvd.domain.builder;

import com.solvd.domain.Client;
import com.solvd.domain.Room;
import com.solvd.domain.Stand;

import java.math.BigDecimal;

public class StandBuilder extends BaseEntityBuilder<StandBuilder, Stand> {
    private BigDecimal price;
    private Room room = new Room();
    private Client client = new Client();

    public StandBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public StandBuilder room(Room room) {
        this.room = room;
        return this;
    }

    public StandBuilder client(Client client) {
        this.client = client;
        return this;
    }

    @Override
    public Stand build() {
        Stand stand = new Stand();
        stand.setId(id);
        stand.setPrice(price);
        stand.setRoom(room);
        stand.setClient(client);
        return stand;
    }
}
