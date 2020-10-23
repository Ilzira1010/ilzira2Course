package ru.itis.repositories;

import ru.itis.models.User;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByLogin(String login, String table);

    Boolean findUsersByLoginAndPassword(String email, String password, String table);
}
