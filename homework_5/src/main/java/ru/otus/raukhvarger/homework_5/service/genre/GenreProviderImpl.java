package ru.otus.raukhvarger.homework_5.service.genre;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.db.GenreRepository;
import ru.otus.raukhvarger.homework_5.entity.Genre;

@Service
public class GenreProviderImpl implements GenreProvider {

    private final GenreRepository dbGenreProvider;

    public GenreProviderImpl(GenreRepository provider) {
        this.dbGenreProvider = provider;
    }

    @Override
    public void createGenre(Genre genre) {
        dbGenreProvider.insertGenre(genre);
    }

    @Override
    public Genre getExistingGenreById(Integer genreId) {
        return dbGenreProvider.getExistingGenreById(genreId);
    }

    @Override
    public Genre getGenreByName(String genreName) {
        return dbGenreProvider.getGenreByName(genreName);
    }

    @Override
    public Genre getOrCreateGenreByName(String genreName) {
        if (dbGenreProvider.getGenreByName(genreName) == null) dbGenreProvider.insertGenre(new Genre(genreName));
        return dbGenreProvider.getGenreByName(genreName);
    }
}
