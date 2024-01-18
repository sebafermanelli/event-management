package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Visitor;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.VisitorRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class VisitorRepositoryJdbcImpl extends AbstractRepository<Visitor> implements VisitorRepository {
    @Override
    public void save(Visitor visitor) {
        String sql = "Insert into visitor (cuil, first_name, last_name, address, phone, email) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, visitor.getCuil());
            stmt.setString(2, visitor.getFirstName());
            stmt.setString(3, visitor.getLastName());
            stmt.setString(4, visitor.getAddress());
            stmt.setString(5, visitor.getPhone());
            stmt.setString(6, visitor.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Visitor> findAll() {
        String sql = "Select * from visitor";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Visitor> visitors = new ArrayList<>();
            while (resultSet.next()) {
                Visitor visitor = Visitor.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .build();
                visitors.add(visitor);
            }
            return visitors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Visitor> findById(Long id) {
        String sql = "Select * from visitor where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Visitor visitor = Visitor.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .build();
                return Optional.of(visitor);
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
    public Collection<Visitor> findManyByColumn(String key, String value) {
        String sql = "Select * from visitor where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Visitor> visitors = new ArrayList<>();
            while (resultSet.next()) {
                Visitor visitor = Visitor.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .build();
                visitors.add(visitor);
            }
            return visitors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Visitor> findOneByColumn(String key, String value) {
        String sql = "Select * from visitor where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Visitor visitor = Visitor.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .build();
                return Optional.of(visitor);
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
        String sql = "Delete from visitor where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The visitor with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Visitor visitor, Long id) {
        String sql = "Update visitor set cuil = ?, first_name = ?, last_name = ?, address = ?, phone = ?, email = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, visitor.getCuil());
            stmt.setString(2, visitor.getFirstName());
            stmt.setString(3, visitor.getLastName());
            stmt.setString(4, visitor.getAddress());
            stmt.setString(5, visitor.getPhone());
            stmt.setString(6, visitor.getEmail());
            stmt.setLong(7, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The visitor with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
