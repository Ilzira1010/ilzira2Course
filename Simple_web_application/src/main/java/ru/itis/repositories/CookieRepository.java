package ru.itis.repositories;

import javax.servlet.http.Cookie;

public interface CookieRepository {
    void addCookie(String uuid, Integer id);

    Cookie[] getCookie(String key);
}
