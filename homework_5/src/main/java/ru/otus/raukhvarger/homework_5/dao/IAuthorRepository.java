package ru.otus.raukhvarger.homework_5.dao;

import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;

import java.util.List;

public interface IAuthorRepository extends ICommonRepository<AuthorEntity> {

    List<AuthorEntity> findAllByFirstNameLike(String firstName);

    List<AuthorEntity> findAllByLastNameLike(String lastName);

}
