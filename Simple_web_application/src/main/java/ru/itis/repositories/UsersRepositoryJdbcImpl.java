package ru.itis.repositories;

import ru.itis.models.User;

import javax.servlet.http.Cookie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(User entity, String table) {
        try {
            PreparedStatement resultAuthorization = connection.prepareStatement("insert into " + table + " (firstName, lastName, email, username, password) values (?,?,?,?,?);");
            resultAuthorization.setString(1, entity.getFirstName());
            resultAuthorization.setString(2, entity.getLastName());
            resultAuthorization.setString(3, entity.getEmail());
            resultAuthorization.setString(4, entity.getUsername());
            resultAuthorization.setString(5, entity.getPassword());
            resultAuthorization.executeUpdate();
            resultAuthorization.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll(String table) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from " + table);
            List<User> users = new ArrayList<>();
            while (result.next()) {
                users.add(User.builder()
                        .firstName(result.getString("firstName"))
                        .lastName(result.getString("lastName"))
                        .email(result.getString("email"))
                        .username(result.getString("username"))
                        .password(result.getString("password"))
                        .build());
            }
            return users;
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }

    @Override
    public Optional<User> findById(long id, String table) {

        return Optional.empty();
    }

    public Boolean findUsersByLoginAndPassword(String email, String password, String table) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from " + table + " where email = ? and password = ?");
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<User> findByLogin(String login, String table) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from " + table + " where email = '" + login + "'");
            if (!result.next())
                return Optional.empty();
            User newUser = User.builder()
                    .id(result.getInt("id"))
                    .email(result.getString("email"))
                    .password(result.getString("password"))
                    .build();
            return Optional.of(newUser);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void saveCookie(Cookie entity, Integer id, String table) {
        try {
            PreparedStatement resultAuthorization = connection.prepareStatement("insert into " + table + " (id, uuid) values (?,?);");
            resultAuthorization.setInt(1, id);
            resultAuthorization.setString(2, entity.getValue());
            resultAuthorization.executeUpdate();
            resultAuthorization.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}

