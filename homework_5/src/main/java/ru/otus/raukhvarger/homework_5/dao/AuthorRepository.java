package ru.otus.raukhvarger.homework_5.dao;

import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<AuthorEntity> findAll();

    List<AuthorEntity> findAllByFirstNameLike(String firstName);

    List<AuthorEntity> findAllByLastNameLike(String lastName);

    Long saveAndReturnId(AuthorEntity author);

    Optional<AuthorEntity> findOne(Long id);

    void update(AuthorEntity author);

    void delete(Long id);
}
