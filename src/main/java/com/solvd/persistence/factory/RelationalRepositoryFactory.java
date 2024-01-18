package com.solvd.persistence.factory;

import com.solvd.persistence.*;
import com.solvd.persistence.impl.jdbc.*;
import com.solvd.persistence.impl.mybatis.*;

public class RelationalRepositoryFactory implements RepositoryFactory {
    @Override
    public ClientRepository createClientRepository(String type) {
        ClientRepository result;

        switch (type) {
            case "JDBC":
                result = new ClientRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new ClientRepositoryMybatisImpl();
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
            case "JDBC":
                result = new EmployeeRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new EmployeeRepositoryMybatisImpl();
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
            case "JDBC":
                result = new EventRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new EventRepositoryMybatisImpl();
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
            case "JDBC":
                result = new PresentationRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new PresentationRepositoryMybatisImpl();
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
            case "JDBC":
                result = new PresenterRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new PresenterRepositoryMybatisImpl();
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
            case "JDBC":
                result = new RoomRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new RoomRepositoryMybatisImpl();
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
            case "JDBC":
                result = new StandRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new StandRepositoryMybatisImpl();
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
            case "JDBC":
                result = new TicketRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new TicketRepositoryMybatisImpl();
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
            case "JDBC":
                result = new VisitorRepositoryJdbcImpl();
                break;
            case "MYBATIS":
                result = new VisitorRepositoryMybatisImpl();
                break;
            default:
                throw new RuntimeException(String.format("Unable to create an object related to the '%s' type",
                        type));
        }
        return result;
    }
}
