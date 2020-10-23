package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity, String table);

    List<T> findAll(String table);

    Optional<T> findById(long id, String table);
}
