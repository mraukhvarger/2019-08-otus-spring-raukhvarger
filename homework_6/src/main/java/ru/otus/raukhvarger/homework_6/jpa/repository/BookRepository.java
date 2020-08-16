package ru.otus.raukhvarger.homework_6.jpa.repository;

import ru.otus.raukhvarger.homework_6.jpa.entity.BookEntity;

import java.util.List;

public interface BookRepository {
    BookEntity getById(Long bookId);

    List<BookEntity> getByName(String bookName);

    void insert(BookEntity bookEntity);

    void update(BookEntity bookEntity);

    void deleteById(Long bookId);

    List<BookEntity> getAll();
}
