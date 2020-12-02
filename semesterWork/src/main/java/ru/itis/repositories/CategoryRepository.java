package ru.itis.repositories;

import ru.itis.models.Category;

import java.util.List;

public interface CategoryRepository {
    Category getById(long id);
    List<Category> getAll();
}
