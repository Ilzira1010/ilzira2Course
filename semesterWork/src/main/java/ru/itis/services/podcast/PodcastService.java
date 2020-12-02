package ru.itis.services.podcast;

import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;
import ru.itis.dto.UserDto;
import ru.itis.models.Category;
import ru.itis.models.Podcast;

import java.util.List;
import java.util.Optional;

public interface PodcastService {
    List<Podcast> getAll();
    List<Podcast> getUserPodcasts(UserDto user);
    List<Podcast> find(String search);
    List<Podcast> findByCategory(String search, Category category);
    Optional<Podcast> getById(long id);
    void safe(Podcast podcast);
    void deleteById(long id);
    void update(Podcast podcast, long id);
}
