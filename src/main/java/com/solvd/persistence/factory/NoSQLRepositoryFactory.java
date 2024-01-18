package com.solvd.persistence.factory;

import com.solvd.persistence.*;

public class NoSQLRepositoryFactory implements RepositoryFactory {
    @Override
    public ClientRepository createClientRepository(String type) {
        ClientRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new ClientRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public EmployeeRepository createEmployeeRepository(String type) {
        EmployeeRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new EmployeeRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public EventRepository createEventRepository(String type) {
        EventRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new EventRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public PresentationRepository createPresentationRepository(String type) {
        PresentationRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new PresentationRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public PresenterRepository createPresenterRepository(String type) {
        PresenterRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new PresenterRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public RoomRepository createRoomRepository(String type) {
        RoomRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new RoomRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public StandRepository createStandRepository(String type) {
        StandRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new StandRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public TicketRepository createTicketRepository(String type) {
        TicketRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new TicketRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }

    @Override
    public VisitorRepository createVisitorRepository(String type) {
        VisitorRepository result;

        switch (type) {
            case "MONGODB":
                result = null;//new VisitorRepositoryMongoDbImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }
}
