package ru.itis.services.users;

import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Optional<UserDto> getUserByCookie(String value) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return usersRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        if (!usersRepository.findUserByEmail(user.getEmail()).isPresent()) {
            usersRepository.save(user);
        }
    }

}
