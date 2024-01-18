package com.solvd.persistence.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PersistenceConfigJdbc {
    private static final String URL = "jdbc:mysql://localhost:3306/event_management";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final int POOL_SIZE = 5;
    private static BlockingQueue<Connection> connections;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connections = new LinkedBlockingQueue<>(POOL_SIZE);
            initializePool();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void initializePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                connections.offer(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public static void releaseConnection(Connection connection) {
        connections.offer(connection);
    }
}
