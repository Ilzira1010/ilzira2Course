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
    public Optional<UserDto> getUserByEmail(String email) {
        User user = usersRepository.findUserByEmail(email).orElse(null);
        if (user != null) {
            return Optional.ofNullable(UserDto.builder().id(user.getId()).email(user.getEmail()).nickname(user.getNickname()).build());
        }
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
