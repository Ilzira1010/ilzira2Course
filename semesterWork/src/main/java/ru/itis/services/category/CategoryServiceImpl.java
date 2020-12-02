package ru.itis.services.category;

import ru.itis.models.Category;
import ru.itis.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Optional<Category> getById(long id) {
        return Optional.ofNullable(categoryRepository.getById(id));
    }
}
