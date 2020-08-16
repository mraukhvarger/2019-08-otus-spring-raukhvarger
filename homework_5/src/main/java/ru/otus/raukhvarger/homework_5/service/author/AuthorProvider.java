package ru.otus.raukhvarger.homework_5.service.author;

import ru.otus.raukhvarger.homework_5.entity.Author;

public interface AuthorProvider {
    void createAuthor(Author author);

    Author getExistingAuthorById(Integer authorId);

    Author getAuthorByName(String authorName);

    Author getOrCreateAuthorByName(String authorName);
}
