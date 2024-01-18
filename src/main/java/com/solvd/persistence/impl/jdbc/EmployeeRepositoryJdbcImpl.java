package com.solvd.persistence.impl.jdbc;

import com.solvd.domain.Employee;
import com.solvd.exception.ResourceNotFoundException;
import com.solvd.persistence.AbstractRepository;
import com.solvd.persistence.EmployeeRepository;
import com.solvd.persistence.config.PersistenceConfigJdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class EmployeeRepositoryJdbcImpl extends AbstractRepository<Employee> implements EmployeeRepository {
    @Override
    public void save(Employee employee) {
        String sql = "Insert into employee (cuil, first_name, last_name, address, phone, email, salary) values (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, employee.getCuil());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getAddress());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getEmail());
            stmt.setBigDecimal(7, employee.getSalary());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    employee.setId(id);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Employee> findAll() {
        String sql = "Select * from employee";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();
            Collection<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .salary(resultSet.getBigDecimal(8))
                        .build();
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Employee> findById(Long id) {
        String sql = "Select * from employee where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .salary(resultSet.getBigDecimal(8))
                        .build();
                return Optional.of(employee);
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
    public Collection<Employee> findManyByColumn(String key, String value) {
        String sql = "Select * from employee where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .salary(resultSet.getBigDecimal(8))
                        .build();
                employees.add(employee);
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Optional<Employee> findOneByColumn(String key, String value) {
        String sql = "Select * from employee where " + key + " = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, value);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Employee employee = Employee.builder()
                        .id(resultSet.getLong(1))
                        .cuil(resultSet.getString(2))
                        .firstName(resultSet.getString(3))
                        .lastName(resultSet.getString(4))
                        .address(resultSet.getString(5))
                        .phone(resultSet.getString(6))
                        .email(resultSet.getString(7))
                        .salary(resultSet.getBigDecimal(8))
                        .build();
                return Optional.of(employee);
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
        String sql = "Delete from employee where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The employee with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public void updateById(Employee employee, Long id) {
        String sql = "Update employee set cuil = ?, first_name = ?, last_name = ?, address = ?, phone = ?, email = ?, salary = ? where id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, employee.getCuil());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getAddress());
            stmt.setString(5, employee.getPhone());
            stmt.setString(6, employee.getEmail());
            stmt.setBigDecimal(7, employee.getSalary());
            stmt.setLong(8, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected != 1) {
                throw new ResourceNotFoundException("The employee with the id " + id + " not found in the database");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }

    @Override
    public Collection<Employee> findManyByEventId(Long eventId) {
        String sql = "Select * from event_employee where event_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, eventId);
            ResultSet resultSet = stmt.executeQuery();
            Collection<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Optional<Employee> employee = findById(resultSet.getLong(2));
                employee.ifPresent(employees::add);
            }
            return employees;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            PersistenceConfigJdbc.releaseConnection(connection);
        }
    }
}
