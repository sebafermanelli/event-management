package com.solvd.domain;

import com.solvd.domain.builder.StandBuilder;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;

@XmlRootElement(name = "stand")
@XmlAccessorType(XmlAccessType.FIELD)
public class Stand extends BaseEntity {
    private BigDecimal price;
    private Room room;
    private Client client;

    public Stand() {
        this.room = new Room();
        this.client = new Client();
    }

    public static StandBuilder builder() {
        return new StandBuilder();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
