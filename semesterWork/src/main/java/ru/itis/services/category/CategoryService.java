package ru.itis.services.category;

import ru.itis.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAll();
    Optional<Category> getById(long id);
}
