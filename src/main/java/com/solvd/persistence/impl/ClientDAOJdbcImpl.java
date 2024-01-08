package com.solvd.persistence.impl;

import com.solvd.domain.Client;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.ClientDAO;
import com.solvd.persistence.PersistenceConfigJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ClientDAOJdbcImpl extends AbstractDAO<Client> implements ClientDAO {

    public ClientDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Client client) {
        String sql = "Insert into client (cuit, business_name, address, phone, email) values (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getCuit());
            stmt.setString(2, client.getBusinessName());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getEmail());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    client.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Client> findAll() {
        String sql = "Select * from client";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(1));
                client.setCuit(resultSet.getString(2));
                client.setBusinessName(resultSet.getString(3));
                client.setAddress(resultSet.getString(4));
                client.setPhone(resultSet.getString(5));
                client.setEmail(resultSet.getString(6));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Client> findById(Long id) {
        String sql = "Select * from client where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(1));
                client.setCuit(resultSet.getString(2));
                client.setBusinessName(resultSet.getString(3));
                client.setAddress(resultSet.getString(4));
                client.setPhone(resultSet.getString(5));
                client.setEmail(resultSet.getString(6));
                return Optional.of(client);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Client> findManyByColumn(String key, String value) {
        String sql = "Select * from client where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(1));
                client.setCuit(resultSet.getString(2));
                client.setBusinessName(resultSet.getString(3));
                client.setAddress(resultSet.getString(4));
                client.setPhone(resultSet.getString(5));
                client.setEmail(resultSet.getString(6));
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Client> findOneByColumn(String key, String value) {
        String sql = "Select * from client where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong(1));
                client.setCuit(resultSet.getString(2));
                client.setBusinessName(resultSet.getString(3));
                client.setAddress(resultSet.getString(4));
                client.setPhone(resultSet.getString(5));
                client.setEmail(resultSet.getString(6));
                return Optional.of(client);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "Delete from client where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Client client, Long id) {
        String sql = "Update client set cuit = ?, business_name = ?, address = ?, phone = ?, email = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getCuit());
            stmt.setString(2, client.getBusinessName());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getEmail());
            stmt.setLong(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
