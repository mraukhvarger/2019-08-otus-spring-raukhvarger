package ru.otus.raukhvarger.homework_6.jpa.repository;

import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;

import java.util.List;

public interface GenreRepository {
    GenreEntity getById(Integer genreId);

    GenreEntity getByName(String genreName);

    void insert(GenreEntity genreEntity);

    void update(GenreEntity genreEntity);

    void deleteById(Integer genreId);

    List<GenreEntity> getAll();
}
