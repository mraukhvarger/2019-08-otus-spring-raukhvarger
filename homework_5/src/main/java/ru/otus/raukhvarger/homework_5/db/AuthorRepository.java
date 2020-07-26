package ru.otus.raukhvarger.homework_5.db;

import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;

public interface AuthorRepository {
    AuthorEntity getExistingAuthorById(Integer authorId);

    AuthorEntity getAuthorByName(String authorName);

    void insertAuthor(AuthorEntity authorEntity);
}
