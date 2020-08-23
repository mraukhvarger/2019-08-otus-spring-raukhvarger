package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.GenreRepository;
import ru.otus.raukhvarger.homework_6.utils.EntityConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreImpl implements GenreProvider {

    private final GenreRepository genreRepository;
    private final EntityConverter entityConverter;

    public GenreImpl(GenreRepository genreRepository, EntityConverter entityConverter) {
        this.genreRepository = genreRepository;
        this.entityConverter = entityConverter;
    }

    @Override
    public void create(GenreDTO genre) {
        genreRepository.insert(genre.buildJpaEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDTO getById(Long id) {
        GenreEntity genreEntity = genreRepository.getById(id);
        return genreEntity != null ? entityConverter.buildDTO(genreEntity) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public GenreDTO getByName(String name) {
        GenreEntity genreEntity = genreRepository.getByName(name);
        return genreEntity != null ? entityConverter.buildDTO(genreEntity) : null;
    }

    @Override
    public GenreDTO getOrCreateByName(String name) {
        if (genreRepository.getByName(name) == null) genreRepository.insert((new GenreDTO(name)).buildJpaEntity());
        return entityConverter.buildDTO(genreRepository.getByName(name));
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
    @Transactional(readOnly = true)
    public List<GenreDTO> getAll() {
        return genreRepository.getAll().stream()
                .map(entityConverter::buildDTO)
                .collect(Collectors.toList());
    }
}
