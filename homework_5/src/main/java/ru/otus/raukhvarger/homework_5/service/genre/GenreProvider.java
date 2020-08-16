package ru.otus.raukhvarger.homework_5.service.genre;

import ru.otus.raukhvarger.homework_5.entity.Genre;

public interface GenreProvider {
    void createGenre(Genre genre);

    Genre getExistingGenreById(Integer genreId);

    Genre getGenreByName(String genreName);

    Genre getOrCreateGenreByName(String genreName);
}
