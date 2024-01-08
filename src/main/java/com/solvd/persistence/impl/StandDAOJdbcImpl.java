package com.solvd.persistence.impl;

import com.solvd.domain.Stand;
import com.solvd.persistence.AbstractDAO;
import com.solvd.persistence.PersistenceConfigJdbc;
import com.solvd.persistence.StandDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class StandDAOJdbcImpl extends AbstractDAO<Stand> implements StandDAO {

    public StandDAOJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public void save(Stand stand) {
//        Nothing here
    }

    @Override
    public Collection<Stand> findAll() {
        String sql = "Select * from stand";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Stand> stands = new ArrayList<>();
            while (resultSet.next()) {
                Stand stand = new Stand();
                stand.setId(resultSet.getLong(1));
                stand.setPrice(resultSet.getLong(2));
                stands.add(stand);
            }
            return stands;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Stand> findById(Long id) {
        String sql = "Select * from stand where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Stand stand = new Stand();
                stand.setId(resultSet.getLong(1));
                stand.setPrice(resultSet.getLong(2));
                return Optional.of(stand);
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
    public Collection<Stand> findManyByColumn(String key, String value) {
        String sql = "Select * from stand where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Stand> stands = new ArrayList<>();
            while (resultSet.next()) {
                Stand stand = new Stand();
                stand.setId(resultSet.getLong(1));
                stand.setPrice(resultSet.getLong(2));
                stands.add(stand);
            }
            return stands;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Stand> findOneByColumn(String key, String value) {
        String sql = "Select * from stand where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Stand stand = new Stand();
                stand.setId(resultSet.getLong(1));
                stand.setPrice(resultSet.getLong(2));
                return Optional.of(stand);
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
        String sql = "Delete from stand where id = ?";
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
    public void updateById(Stand stand, Long id) {
        String sql = "Update stand set price = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, stand.getPrice());
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void save(Stand stand, Long roomId) {
        String sql = "Insert into stand (price, room_id) values (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, stand.getPrice());
            stmt.setLong(2, roomId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    stand.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void addClient(Long standId, Long clientId) {
        String sql = "Update stand set client_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, clientId);
            stmt.setLong(2, standId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
