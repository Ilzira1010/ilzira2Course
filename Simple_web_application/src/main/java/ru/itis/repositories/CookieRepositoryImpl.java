package ru.itis.repositories;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CookieRepositoryImpl implements CookieRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_course_2";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ilzira1010";
    public UsersRepositoryJdbcImpl repositoryJdbc;
    private HttpServletResponse response;
    private HttpServletRequest request;


    public CookieRepositoryImpl(HttpServletResponse response) {
        try {
            this.repositoryJdbc = new UsersRepositoryJdbcImpl(
                    DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)
            );
            this.response = response;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    CookieRepositoryImpl(HttpServletRequest request) {
        try {
            this.repositoryJdbc = new UsersRepositoryJdbcImpl(
                    DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)
            );
            this.request = request;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addCookie(String uuid, Integer id) {
        Cookie cookie = new Cookie("Auth", uuid);
        response.addCookie(cookie);
        repositoryJdbc.saveCookie(cookie, id, "uuid");
    }

    @Override
    public Cookie[] getCookie(String key) {
        return request.getCookies();
    }
}
