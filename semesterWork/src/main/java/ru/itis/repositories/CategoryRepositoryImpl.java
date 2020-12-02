package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.itis.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT * from category where id=?";

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * from category";

    private final RowMapper<Category> categoryRowMapper = (r, i) -> Category.builder()
            .id(r.getLong("id"))
            .name(r.getString("name"))
            .build();

    @Override
    public Category getById(long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, categoryRowMapper, id);
    }

    @Override
    public List<Category> getAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, categoryRowMapper);
    }
}
