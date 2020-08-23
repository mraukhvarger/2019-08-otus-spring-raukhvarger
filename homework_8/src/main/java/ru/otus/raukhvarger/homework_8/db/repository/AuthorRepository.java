package ru.otus.raukhvarger.homework_8.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.raukhvarger.homework_8.db.entity.Author;
import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByAuthorNameIgnoreCase(String authorName);
}
