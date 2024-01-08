package com.solvd.persistence;

import com.solvd.domain.Room;

public interface RoomDAO extends GenericDAO<Room> {
    void save(Room entity, Long eventId);
}
