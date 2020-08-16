package ru.otus.raukhvarger.homework_5.db;

import ru.otus.raukhvarger.homework_5.entity.Genre;

public interface GenreRepository {
    Genre getExistingGenreById(Integer genreId);

    Genre getGenreByName(String genreName);

    void insertGenre(Genre genre);
}
