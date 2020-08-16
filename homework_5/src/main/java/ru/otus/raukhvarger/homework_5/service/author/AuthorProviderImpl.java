package ru.otus.raukhvarger.homework_5.service.author;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.db.AuthorRepository;
import ru.otus.raukhvarger.homework_5.entity.Author;

@Service
public class AuthorProviderImpl implements AuthorProvider {
    private final AuthorRepository dbAuthorProvider;

    public AuthorProviderImpl(AuthorRepository dbAuthorProvider) {
        this.dbAuthorProvider = dbAuthorProvider;
    }

    @Override
    public void createAuthor(Author author) {
        dbAuthorProvider.insertAuthor(author);
    }

    @Override
    public Author getExistingAuthorById(Integer authorId) {
        return dbAuthorProvider.getExistingAuthorById(authorId);
    }

    @Override
    public Author getAuthorByName(String authorName) {
        return dbAuthorProvider.getAuthorByName(authorName);
    }

    @Override
    public Author getOrCreateAuthorByName(String authorName) {
        if (dbAuthorProvider.getAuthorByName(authorName) == null) dbAuthorProvider.insertAuthor(new Author(authorName));
        return dbAuthorProvider.getAuthorByName(authorName);
    }
}
