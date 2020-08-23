package ru.otus.raukhvarger.homework_8.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.raukhvarger.homework_8.db.entity.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
