package com.solvd;

import com.solvd.domain.Client;
import com.solvd.persistence.impl.ClientDAOMybatisImpl;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {
//            JDBC Test
//            Connection conn = PersistenceConfigJdbc.getConnection();
//            ClientDAOJdbcImpl clientDAOJdbc = new ClientDAOJdbcImpl(conn);
//            Collection<Client> clientsJdbc = clientDAOJdbc.findAll();
//            clientsJdbc.forEach(client -> {
//                if (client.getStands().isEmpty()) {
//                    System.out.println(client.getCuit() + " empty");
//                }
//                client.getStands().forEach(stand -> {
//                    System.out.println(client.getCuit() + " " + client.getBusinessName() + " " + stand.getPrice());
//                });
//            });

//            MyBatis Test
        ClientDAOMybatisImpl clientDAOMybatis = new ClientDAOMybatisImpl();

        Optional<Client> myclient = clientDAOMybatis.findById(1L);
        System.out.println(myclient.toString());

        Collection<Client> clientsMyBatis = clientDAOMybatis.findAll();
        System.out.println(clientsMyBatis.size());
        clientsMyBatis.forEach(client -> {
            System.out.println(client.toString());
//            client.getStands().stream().filter(Objects.nonNull()).forEach(stand -> System.out.println(stand.getPrice()));
        });
    }
}