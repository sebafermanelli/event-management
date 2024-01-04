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
        String sql = "Insert into stand (price, room_id, client_id) values (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, stand.getPrice());
            stmt.setLong(2, stand.getRoomId());
            stmt.setLong(3, stand.getClientId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
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
                stand.setRoomId(resultSet.getLong(3));
                stand.setClientId(resultSet.getLong(4));
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
                stand.setRoomId(resultSet.getLong(3));
                stand.setClientId(resultSet.getLong(4));
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
    public Optional<Collection<Stand>> findManyByColumn(String key, String value) {
        String sql = "Select * from stand where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Stand> stands = new ArrayList<>();
            while (resultSet.next()) {
                Stand stand = new Stand();
                stand.setId(resultSet.getLong(1));
                stand.setPrice(resultSet.getLong(2));
                stand.setRoomId(resultSet.getLong(3));
                stand.setClientId(resultSet.getLong(4));
                stands.add(stand);
            }
            return Optional.of(stands);
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
                stand.setRoomId(resultSet.getLong(3));
                stand.setClientId(resultSet.getLong(4));
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
        String sql = "Update stand set price = ?, room_id = ?, client_id = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, stand.getPrice());
            stmt.setLong(2, stand.getRoomId());
            stmt.setLong(3, stand.getClientId());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
