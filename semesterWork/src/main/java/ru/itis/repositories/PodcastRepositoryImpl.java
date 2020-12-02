package ru.itis.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import ru.itis.models.Category;
import ru.itis.models.Podcast;
import ru.itis.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class PodcastRepositoryImpl implements PodcastRepository {

    private final JdbcTemplate jdbcTemplate;

    public PodcastRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT p.id as p_id, p.title as p_title, p.img as p_img, p.track as p_track, p.created_at as " +
            "p_created_at, u.id as u_id,u.hash as hash, u.email as u_email, u.nickname as u_nickname, c.id as c_id, c.name as c_name FROM podcast p INNER JOIN " +
            "users u on p.user_id = u.id INNER JOIN podcast_category pc on p.id = pc.podcast_id INNER JOIN category c on c.id = pc.category_id ORDER BY p.id DESC";

    //language=SQL
    private static final String SQL_FIND_BY_ID = "SELECT p.id as p_id, p.title as p_title, p.img as p_img, p.track as p_track, p.created_at as " +
            "p_created_at, u.id as u_id, u.hash as hash, u.email as u_email, u.nickname as u_nickname, c.id as c_id, c.name as c_name FROM podcast p INNER JOIN " +
            "users u on p.user_id = u.id INNER JOIN podcast_category pc on p.id = pc.podcast_id INNER JOIN category c on c.id = pc.category_id WHERE p.id=?";

    //language=SQL
    private static final String SQL_SEARCH = "SELECT p.id as p_id, p.title as p_title, p.img as p_img, p.track as p_track, p.created_at as " +
            "p_created_at, u.id as u_id, u.hash as hash, u.email as u_email, u.nickname as u_nickname, c.id as c_id, c.name as c_name FROM podcast p INNER JOIN " +
            "users u on p.user_id = u.id INNER JOIN podcast_category pc on p.id = pc.podcast_id INNER JOIN category c on c.id = pc.category_id WHERE p.title like ? ORDER BY p.id DESC";

    //language=SQL
    private static final String SQL_SEARCH_BY_CATEGORY = "SELECT p.id as p_id, p.title as p_title, p.img as p_img, p.track as p_track, p.created_at as " +
            "p_created_at, u.id as u_id, u.hash as hash, u.email as u_email, u.nickname as u_nickname, c.id as c_id, c.name as c_name " +
            "FROM podcast p INNER JOIN users u on p.user_id = u.id INNER JOIN podcast_category pc on p.id = pc.podcast_id inner join category " +
            "c on c.id = pc.category_id WHERE p.title like ? AND c.id=? ORDER BY p.id DESC";

    //language=SQL
    private static final String SQL_FIND_BY_USER = "SELECT p.id as p_id, p.title as p_title, p.img as p_img, p.track as p_track, p.created_at as " +
            "p_created_at, u.id as u_id, u.hash as hash,u.email as u_email, u.nickname as u_nickname,  c.id as c_id, c.name as c_name FROM podcast p INNER JOIN " +
            "users u on p.user_id = u.id INNER JOIN podcast_category pc on p.id = pc.podcast_id inner join category c " +
            "on c.id = pc.category_id WHERE u.id=? ORDER BY p.id DESC";

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO podcast (user_id, title, img, track) VALUES (?,?,?,?)";

    //language=SQL
    private static final String SQL_SAVE_CATEGORY = "INSERT INTO podcast_category VALUES (?,?);";

    //language=SQL
    private static final String SQL_DELETE = "DELETE from podcast_category where podcast_id=?";

    //language=SQL
    private static final String SQL_DELETE_CATEGORY = "DELETE from podcast p where p.id=?";

    //language=SQL
    private static final String SQL_UPDATE = "UPDATE podcast SET title=? where id=?";

    private final RowMapper<Podcast> podcastRowMapper = (r, i) -> Podcast.builder()
            .id(r.getLong("p_id"))
            .user(User.builder()
                    .id(r.getLong("u_id"))
                    .nickname(r.getString("u_nickname"))
                    .email(r.getString("u_email"))
                    .hashPassword(r.getString("hash"))
                    .build())
            .title(r.getString("p_title"))
            .img(r.getString("p_img"))
            .track(r.getString("p_track"))
            .created_at(r.getDate("p_created_at"))
            .category(Category.builder()
                    .id(r.getLong("c_id"))
                    .name(r.getString("c_name"))
                    .build())
            .build();

    @Override
    public List<Podcast> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, podcastRowMapper);
    }

    @Override
    public Podcast findById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, podcastRowMapper, id);
    }

    @Override
    public List<Podcast> find(String search) {
        search = "%" + search + "%";
        return jdbcTemplate.query(SQL_SEARCH, podcastRowMapper, search);
    }

    @Override
    public List<Podcast> findByCategory(String search, Category category) {
        search = "%" + search + "%";
        return jdbcTemplate.query(SQL_SEARCH_BY_CATEGORY, podcastRowMapper, search, category.getId());
    }

    @Override
    public void save(Podcast entity) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, entity.getUser().getId());
            statement.setString(2, entity.getTitle());
            statement.setString(3, entity.getImg());
            statement.setString(4, entity.getTrack());
            return statement;
        }, keyHolder);
        if (rows > 0) {
            System.out.println(keyHolder.getKeys());
            jdbcTemplate.update(SQL_SAVE_CATEGORY, keyHolder.getKeys().get("id"), entity.getCategory().getId());
        }
    }

    @Override
    public void updateById(Podcast entity, long id) {
        jdbcTemplate.update(SQL_UPDATE, entity.getTitle(), id);
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update(SQL_DELETE, id);
        jdbcTemplate.update(SQL_DELETE_CATEGORY, id);
    }

    @Override
    public List<Podcast> getUserPodcasts(User user) {
        return jdbcTemplate.query(SQL_FIND_BY_USER, podcastRowMapper, user.getId());
    }

}
