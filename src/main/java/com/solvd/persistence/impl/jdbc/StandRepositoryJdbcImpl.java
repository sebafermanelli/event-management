package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Client;
import com.solvd.domain.Room;
import com.solvd.domain.Stand;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.StandRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class StandRepositoryJdbcImpl extends AbstractRepository<Stand> implements StandRepository {

    private final RoomRepositoryJdbcImpl roomDAOJdbc = new RoomRepositoryJdbcImpl();
    private final ClientRepositoryJdbcImpl clientDAOJdbc = new ClientRepositoryJdbcImpl();

    @Override
    public void save(Stand stand) {
        String sql = "Insert into stand (price, room_id) values (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setBigDecimal(1, stand.getPrice());
            stmt.setLong(2, stand.getRoom().getId());
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
    public Collection<Stand> findAll() {
        String sql = "Select * from stand";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Stand> stands = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(3));
                Optional<Client> client = clientDAOJdbc.findById(resultSet.getLong(4));
                if (room.isPresent()) {
                    Stand stand = Stand.builder()
                            .id(resultSet.getLong(1))
                            .price(resultSet.getBigDecimal(2))
                            .room(room.get())
                            .client(client.orElse(null))
                            .build();
                    stands.add(stand);
                }
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
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(3));
                Optional<Client> client = clientDAOJdbc.findById(resultSet.getLong(4));
                if (room.isPresent()) {
                    Stand stand = Stand.builder()
                            .id(resultSet.getLong(1))
                            .price(resultSet.getBigDecimal(2))
                            .room(room.get())
                            .client(client.orElse(null))
                            .build();
                    return Optional.of(stand);
                }
            }
            return Optional.empty();
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
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(3));
                Optional<Client> client = clientDAOJdbc.findById(resultSet.getLong(4));
                if (room.isPresent()) {
                    Stand stand = Stand.builder()
                            .id(resultSet.getLong(1))
                            .price(resultSet.getBigDecimal(2))
                            .room(room.get())
                            .client(client.orElse(null))
                            .build();
                    stands.add(stand);
                }
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
                Optional<Room> room = roomDAOJdbc.findById(resultSet.getLong(3));
                Optional<Client> client = clientDAOJdbc.findById(resultSet.getLong(4));
                if (room.isPresent()) {
                    Stand stand = Stand.builder()
                            .id(resultSet.getLong(1))
                            .price(resultSet.getBigDecimal(2))
                            .room(room.get())
                            .client(client.orElse(null))
                            .build();
                    return Optional.of(stand);
                }
            }
            return Optional.empty();
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
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The stand with the id " + id + " not found in the database");
            }
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
            stmt.setBigDecimal(1, stand.getPrice());
            stmt.setLong(2, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The stand with the id " + id + " not found in the database");
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
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The stand with the id " + standId + " or the client with id " + clientId + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void removeClient(Long standId) {
        String sql = "Update stand set client_id = NULL where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, standId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The stand with the id " + standId + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
