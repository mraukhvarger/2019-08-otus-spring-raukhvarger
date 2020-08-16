package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.GenreRepository;
import ru.otus.raukhvarger.homework_6.utils.EntityConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreImpl implements GenreProvider {

    private final GenreRepository genreRepository;

    public GenreImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void create(GenreDTO genre) {
        genreRepository.insert(genre.buildJpaEntity());
    }

    @Override
    public GenreDTO getById(Long id) {
        GenreEntity genreEntity = genreRepository.getById(id);
        return genreEntity != null ? EntityConverter.buildDTO(genreEntity) : null;
    }

    @Override
    public GenreDTO getByName(String name) {
        GenreEntity genreEntity = genreRepository.getByName(name);
        return genreEntity != null ? EntityConverter.buildDTO(genreEntity) : null;
    }

    @Override
    public GenreDTO getOrCreateByName(String name) {
        if (genreRepository.getByName(name) == null) genreRepository.insert((new GenreDTO(name)).buildJpaEntity());
        return EntityConverter.buildDTO(genreRepository.getByName(name));
    }

    @Override
    public void update(GenreDTO author) {
        genreRepository.update(author.buildJpaEntity());
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<GenreDTO> getAll() {
        return genreRepository.getAll().stream()
                .map(EntityConverter::buildDTO)
                .collect(Collectors.toList());
    }
}
