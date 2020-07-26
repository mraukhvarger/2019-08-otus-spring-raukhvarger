package ru.otus.raukhvarger.homework_5.service.genre;

import ru.otus.raukhvarger.homework_5.entity.GenreEntity;

public interface GenreProvider {
    void createGenre(GenreEntity genreEntity);

    GenreEntity getExistingGenreById(Integer genreId);

    GenreEntity getGenreByName(String genreName);

    GenreEntity getOrCreateGenreByName(String genreName);
}
