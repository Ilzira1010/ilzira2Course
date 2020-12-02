package ru.itis.repositories;

import ru.itis.models.Category;
import ru.itis.models.Podcast;
import ru.itis.models.User;

import java.util.List;

public interface PodcastRepository extends CrudRepository<Podcast>{
    List<Podcast> getUserPodcasts(User user);
    List<Podcast> find(String search);
    List<Podcast> findByCategory(String search, Category category);
    void deleteById(long id);
    void updateById(Podcast podcast, long id);
}
