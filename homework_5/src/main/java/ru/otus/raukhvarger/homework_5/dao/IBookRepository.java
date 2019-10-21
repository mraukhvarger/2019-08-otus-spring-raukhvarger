package ru.otus.raukhvarger.homework_5.dao;

import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface IBookRepository {

    List<BookEntity> findAllByCaptionLike(String caption);

    List<BookEntity> findAllByGenreEq(Integer genreId);

    List<BookEntity> findAll();

    Long saveAndReturnId(BookEntity book);

    Optional<BookEntity> findOne(Long id);

    void update(BookEntity book);

    void delete(Long id);
}
