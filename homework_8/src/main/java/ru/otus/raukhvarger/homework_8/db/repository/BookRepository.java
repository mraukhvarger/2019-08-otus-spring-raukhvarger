package ru.otus.raukhvarger.homework_8.db.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.raukhvarger.homework_8.db.entity.Book;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAllByAuthorAuthorId(String authorId);
    List<Book> findByBookName(String bookName);
}
