package ru.otus.raukhvarger.homework_5.db;

import ru.otus.raukhvarger.homework_5.entity.GenreEntity;

public interface GenreRepository {
    GenreEntity getExistingGenreById(Integer genreId);

    GenreEntity getGenreByName(String genreName);

    void insertGenre(GenreEntity genreEntity);
}
