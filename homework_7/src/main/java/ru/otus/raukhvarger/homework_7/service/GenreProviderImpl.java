package ru.otus.raukhvarger.homework_7.service;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_7.dto.GenreDTO;
import ru.otus.raukhvarger.homework_7.jpa.entity.Genre;
import ru.otus.raukhvarger.homework_7.jpa.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreProviderImpl implements GenreProvider {

    private final GenreRepository genreRepository;

    public GenreProviderImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void create(GenreDTO genre) {
        genreRepository.save(genre.buildJpaEntity());
    }

    @Override
    public GenreDTO getById(Integer id) {
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
        if (genreRepository.findByGenreName(name) == null) genreRepository.save(
                GenreDTO.builder()
                        .genreName(name)
                        .build()
                        .buildJpaEntity()
        );
        Genre genre = genreRepository.findByGenreName(name).orElse(null);
        return genre != null ? genre.buildDTO() : null;
    }

    @Override
    public void update(GenreDTO author) {
        genreRepository.save(author.buildJpaEntity());
    }

    @Override
    public void deleteById(Integer id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepository.findAll().stream()
                .map(g -> g.buildDTO())
                .collect(Collectors.toList());
    }
}
