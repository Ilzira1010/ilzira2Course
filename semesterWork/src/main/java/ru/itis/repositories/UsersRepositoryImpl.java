package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryImpl implements UsersRepository {

    //language=SQL
    private final static String SQL_INSERT = "insert into users(nickname, email, hash) " +
            "values (?, ?, ?)";

    //language=SQL
    private final static String SQL_SELECT_ALL = "select * from users";

    private final static String SQL_FIND_BY_EMAIL = "select *from users where email = ?";

    private final JdbcTemplate jdbcTemplate;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final RowMapper<User> usersRowMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .nickname(row.getString("nickname"))
            .email(row.getString("email"))
            .hashPassword(row.getString("hash"))
            .build();

    public void save(User entity) {
        jdbcTemplate.update(SQL_INSERT, entity.getNickname(),
                entity.getEmail(),
                entity.getHashPassword());
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, usersRowMapper);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return jdbcTemplate.query(SQL_FIND_BY_EMAIL, usersRowMapper, email).stream().findFirst();
    }
}