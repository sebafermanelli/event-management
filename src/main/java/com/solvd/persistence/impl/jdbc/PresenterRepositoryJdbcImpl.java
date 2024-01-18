package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Presenter;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.PresenterRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class PresenterRepositoryJdbcImpl extends AbstractRepository<Presenter> implements PresenterRepository {
    @Override
    public void save(Presenter presenter) {
        String sql = "Insert into presenter (cuil, first_name, last_name, address, phone, email, specialization) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, presenter.getCuil());
            stmt.setString(2, presenter.getFirstName());
            stmt.setString(3, presenter.getLastName());
            stmt.setString(4, presenter.getAddress());
            stmt.setString(5, presenter.getPhone());
            stmt.setString(6, presenter.getEmail());
            stmt.setString(7, presenter.getSpecialization());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    presenter.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Presenter> findAll() {
        String sql = "Select * from presenter";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Presenter> presenters = new ArrayList<>();
            while (resultSet.next()) {
                Presenter presenter = Presenter.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .specialization(resultSet.getString(8))
                        .build();
                presenters.add(presenter);
            }
            return presenters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Presenter> findById(Long id) {
        String sql = "Select * from presenter where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Presenter presenter = Presenter.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .specialization(resultSet.getString(8))
                        .build();
                return Optional.of(presenter);
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
    public Collection<Presenter> findManyByColumn(String key, String value) {
        String sql = "Select * from presenter where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Presenter> presenters = new ArrayList<>();
            while (resultSet.next()) {
                Presenter presenter = Presenter.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .specialization(resultSet.getString(8))
                        .build();
                presenters.add(presenter);
            }
            return presenters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Presenter> findOneByColumn(String key, String value) {
        String sql = "Select * from presenter where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Presenter presenter = Presenter.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .specialization(resultSet.getString(8))
                        .build();
                return Optional.of(presenter);
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
        String sql = "Delete from presenter where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The presenter with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Presenter presenter, Long id) {
        String sql = "Update presenter set cuil = ?, first_name = ?, last_name = ?, address = ?, phone = ?, email = ?, specialization = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, presenter.getCuil());
            stmt.setString(2, presenter.getFirstName());
            stmt.setString(3, presenter.getLastName());
            stmt.setString(4, presenter.getAddress());
            stmt.setString(5, presenter.getPhone());
            stmt.setString(6, presenter.getEmail());
            stmt.setString(7, presenter.getSpecialization());
            stmt.setLong(8, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The presenter with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
