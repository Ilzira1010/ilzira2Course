package ru.itis.services.users;

import ru.itis.dto.UserDto;
import ru.itis.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<UserDto> getUserByCookie(String value);
    List<User> getAll();
    void addUser(User user);
}
