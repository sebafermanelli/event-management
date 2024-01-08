package com.solvd;

import com.solvd.domain.Client;
import com.solvd.service.impl.ClientServiceJdbcImpl;

import java.util.Collection;

public class Main {

    public static void main(String[] args) {
        ClientServiceJdbcImpl clientServiceJdbc = new ClientServiceJdbcImpl();
        Collection<Client> clients = clientServiceJdbc.getAll();
        for (Client client : clients) {
            System.out.println(client.toString());
        }
    }
}