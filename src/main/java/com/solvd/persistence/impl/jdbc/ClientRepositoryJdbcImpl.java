package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Client;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.ClientRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class ClientRepositoryJdbcImpl extends AbstractRepository<Client> implements ClientRepository {
    @Override
    public void save(Client client) {
        String sql = "Insert into client (cuit, business_name, address, phone, email) values (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
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
                Client client = Client.builder()
                        .id(resultSet.getLong(1))
                        .cuit(resultSet.getString(2))
                        .businessName(resultSet.getString(3))
                        .address(resultSet.getString(4))
                        .phone(resultSet.getString(5))
                        .email(resultSet.getString(6))
                        .build();
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
                Client client = Client.builder()
                        .id(resultSet.getLong(1))
                        .cuit(resultSet.getString(2))
                        .businessName(resultSet.getString(3))
                        .address(resultSet.getString(4))
                        .phone(resultSet.getString(5))
                        .email(resultSet.getString(6))
                        .build();
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
                Client client = Client.builder()
                        .id(resultSet.getLong(1))
                        .cuit(resultSet.getString(2))
                        .businessName(resultSet.getString(3))
                        .address(resultSet.getString(4))
                        .phone(resultSet.getString(5))
                        .email(resultSet.getString(6))
                        .build();
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
                Client client = Client.builder()
                        .id(resultSet.getLong(1))
                        .cuit(resultSet.getString(2))
                        .businessName(resultSet.getString(3))
                        .address(resultSet.getString(4))
                        .phone(resultSet.getString(5))
                        .email(resultSet.getString(6))
                        .build();
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
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The client with the id " + id + " not found in the database");
            }
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
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The client with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
