package ru.otus.raukhvarger.homework_5.dao;

import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import java.util.List;

public interface IGenreRepository extends ICommonRepository<GenreEntity> {

    List<GenreEntity> findAllByGenreLike(String genre);

}
