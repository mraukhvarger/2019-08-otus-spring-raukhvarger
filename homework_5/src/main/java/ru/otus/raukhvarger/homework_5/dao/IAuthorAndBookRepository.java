package ru.otus.raukhvarger.homework_5.dao;

import ru.otus.raukhvarger.homework_5.entitiy.AuthorAndBookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;

import java.util.List;

public interface IAuthorAndBookRepository extends ICommonRepository<AuthorAndBookEntity> {

    void save(AuthorEntity author, BookEntity book);

    void save(Integer authorId, Integer bookId);

    void delete(AuthorEntity author, BookEntity book);

    void delete(Integer authorId, Integer bookId);

    List<BookEntity> findAllBooksByAuthorId(Integer authorId);

    List<AuthorEntity> findAllAuthorsByBookId(Integer bookId);
}
