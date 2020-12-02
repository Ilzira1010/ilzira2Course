package ru.itis.services.signUp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

public class SignUpServiceImpl implements SignUpService{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean signUp(SignUpForm form) {
        User user = User.builder()
                .nickname(form.getNickname())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();
         usersRepository.save(user);
         return true;
    }
}
