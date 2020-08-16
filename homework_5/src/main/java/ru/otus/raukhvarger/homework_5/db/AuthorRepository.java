package ru.otus.raukhvarger.homework_5.db;

import ru.otus.raukhvarger.homework_5.entity.Author;

public interface AuthorRepository {
    Author getExistingAuthorById(Integer authorId);

    Author getAuthorByName(String authorName);

    void insertAuthor(Author author);
}
