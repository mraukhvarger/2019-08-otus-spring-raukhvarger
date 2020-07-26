package ru.otus.raukhvarger.homework_5.service.genre;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.db.GenreRepository;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;

@Service
public class GenreImpl implements GenreProvider {

    private final GenreRepository dbGenreProvider;

    public GenreImpl(GenreRepository provider) {
        this.dbGenreProvider = provider;
    }

    @Override
    public void createGenre(GenreEntity genreEntity) {
        dbGenreProvider.insertGenre(genreEntity);
    }

    @Override
    public GenreEntity getExistingGenreById(Integer genreId) {
        return dbGenreProvider.getExistingGenreById(genreId);
    }

    @Override
    public GenreEntity getGenreByName(String genreName) {
        return dbGenreProvider.getGenreByName(genreName);
    }

    @Override
    public GenreEntity getOrCreateGenreByName(String genreName) {
        if (dbGenreProvider.getGenreByName(genreName) == null) dbGenreProvider.insertGenre(new GenreEntity(genreName));
        return dbGenreProvider.getGenreByName(genreName);
    }
}
