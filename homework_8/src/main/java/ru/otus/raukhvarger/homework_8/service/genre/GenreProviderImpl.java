package ru.otus.raukhvarger.homework_8.service.genre;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_8.db.entity.Genre;
import ru.otus.raukhvarger.homework_8.db.repository.GenreRepository;
import ru.otus.raukhvarger.homework_8.dto.GenreDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GenreProviderImpl implements GenreProvider {

    private final GenreRepository genreRepository;

    public GenreProviderImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void create(GenreDTO genre) {
        genreRepository.save(genre.buildDBEntity());
    }

    @Override
    public GenreDTO getById(String id) {
        Genre genre = genreRepository.findById(id).orElse(null);
        return genre != null ? genre.buildDTO() : null;
    }

    @Override
    public GenreDTO getByName(String name) {
        Genre genre = genreRepository.findByGenreName(name).orElse(null);
        return genre != null ? genre.buildDTO() : null;
    }

    @Override
    public GenreDTO getOrCreateByName(String name) {
        if (genreRepository.findByGenreName(name).orElse(null) == null) genreRepository.save(
                GenreDTO.builder()
                        .genreId(UUID.randomUUID().toString())
                        .genreName(name)
                        .build()
                        .buildDBEntity()
        );
        Genre genre = genreRepository.findByGenreName(name).orElse(null);
        return genre != null ? genre.buildDTO() : null;
    }

    @Override
    public void update(GenreDTO author) {
        genreRepository.save(author.buildDBEntity());
    }

    @Override
    public void deleteById(String id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepository.findAll().stream()
                .map(g -> g.buildDTO())
                .collect(Collectors.toList());
    }
}
