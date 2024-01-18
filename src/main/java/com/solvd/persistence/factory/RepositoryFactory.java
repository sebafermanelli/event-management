package com.solvd.persistence.factory;

import com.solvd.persistence.*;

public interface RepositoryFactory {
    ClientRepository createClientRepository(String type);

    EmployeeRepository createEmployeeRepository(String type);

    EventRepository createEventRepository(String type);

    PresentationRepository createPresentationRepository(String type);

    PresenterRepository createPresenterRepository(String type);

    RoomRepository createRoomRepository(String type);

    StandRepository createStandRepository(String type);

    TicketRepository createTicketRepository(String type);

    VisitorRepository createVisitorRepository(String type);
}
