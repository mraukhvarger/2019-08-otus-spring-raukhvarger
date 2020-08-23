package ru.otus.raukhvarger.homework_8.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.raukhvarger.homework_8.db.entity.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByGenreName(String genreName);
}

