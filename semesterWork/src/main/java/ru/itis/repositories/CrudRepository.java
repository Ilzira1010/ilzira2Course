package ru.itis.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    void save(T entity);
    T findById(Long id);
    List<T> findAll();
}
