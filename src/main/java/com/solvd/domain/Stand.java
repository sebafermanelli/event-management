package com.solvd.domain;

public class Stand extends BaseEntity {
    private Long price;
    private Long roomId;
    private Long clientId;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Stand{" +
                "price=" + price +
                ", roomId=" + roomId +
                ", clientId=" + clientId +
                ", id=" + id +
                '}';
    }
}
