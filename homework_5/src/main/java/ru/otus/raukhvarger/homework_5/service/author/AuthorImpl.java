package ru.otus.raukhvarger.homework_5.service.author;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.db.AuthorRepository;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;

@Service
public class AuthorImpl implements AuthorProvider {
    private final AuthorRepository dbAuthorProvider;

    public AuthorImpl(AuthorRepository dbAuthorProvider) {
        this.dbAuthorProvider = dbAuthorProvider;
    }

    @Override
    public void createAuthor(AuthorEntity authorEntity) {
        dbAuthorProvider.insertAuthor(authorEntity);
    }

    @Override
    public AuthorEntity getExistingAuthorById(Integer authorId) {
        return dbAuthorProvider.getExistingAuthorById(authorId);
    }

    @Override
    public AuthorEntity getAuthorByName(String authorName) {
        return dbAuthorProvider.getAuthorByName(authorName);
    }

    @Override
    public AuthorEntity getOrCreateAuthorByName(String authorName) {
        if (dbAuthorProvider.getAuthorByName(authorName) == null) dbAuthorProvider.insertAuthor(new AuthorEntity(authorName));
        return dbAuthorProvider.getAuthorByName(authorName);
    }
}
