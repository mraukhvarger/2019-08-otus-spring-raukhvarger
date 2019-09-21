package ru.otus.raukhvarger.homework_5.dao;

import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;

import java.util.List;

public interface IBookRepository extends ICommonRepository<BookEntity> {

    List<BookEntity> findAllByCaptionLike(String caption);

    List<BookEntity> findAllByGenreEq(Integer genreId);
}
