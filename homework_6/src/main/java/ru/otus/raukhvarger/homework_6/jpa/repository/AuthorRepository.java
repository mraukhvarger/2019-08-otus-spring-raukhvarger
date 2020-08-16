package ru.otus.raukhvarger.homework_6.jpa.repository;

import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;

import java.util.List;

public interface AuthorRepository {
    AuthorEntity getById(Long authorId);

    AuthorEntity getByName(String authorName);

    void insert(AuthorEntity authorEntity);

    void update(AuthorEntity authorEntity);

    void deleteById(Long authorId);

    List<AuthorEntity> getAll();
}
