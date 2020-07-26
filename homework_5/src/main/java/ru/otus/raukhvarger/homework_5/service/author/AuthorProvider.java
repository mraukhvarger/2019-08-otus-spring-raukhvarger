package ru.otus.raukhvarger.homework_5.service.author;

import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;

public interface AuthorProvider {
    void createAuthor(AuthorEntity authorEntity);

    AuthorEntity getExistingAuthorById(Integer authorId);

    AuthorEntity getAuthorByName(String authorName);

    AuthorEntity getOrCreateAuthorByName(String authorName);
}
