package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.AuthorRepository;
import ru.otus.raukhvarger.homework_6.utils.EntityConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorImpl implements AuthorProvider {

    private final AuthorRepository authorRepository;
    private final EntityConverter entityConverter;

    public AuthorImpl(AuthorRepository authorRepository, EntityConverter entityConverter) {
        this.authorRepository = authorRepository;
        this.entityConverter = entityConverter;
    }

    @Override
    public void create(AuthorDTO author) {
        authorRepository.insert(author.buildJpaEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getById(Long id) {
        AuthorEntity authorEntity = authorRepository.getById(id);
        return authorEntity != null ? entityConverter.buildDTO(authorEntity) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getByName(String name) {
        AuthorEntity authorEntity = authorRepository.getByName(name);
        return authorEntity != null ? entityConverter.buildDTO(authorEntity) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getByNameWithBooks(String name) {
        AuthorEntity authorEntity = authorRepository.getByName(name != null ? name.toUpperCase() : null);
        AuthorDTO authorDTO = null;
        if (authorEntity != null) {
            authorDTO = entityConverter.buildDTO(authorEntity);
            authorDTO.setBooks(
                    authorEntity.getBookEntities().stream()
                            .map(entityConverter::buildDTO)
                            .collect(Collectors.toSet())
            );
        }
        return authorDTO;
    }

    @Override
    public AuthorDTO getOrCreateByName(String name) {
        if (authorRepository.getByName(name) == null) authorRepository.insert((new AuthorDTO(name)).buildJpaEntity());
        return entityConverter.buildDTO(authorRepository.getByName(name));
    }

    @Override
    public void update(AuthorDTO author) {
        authorRepository.update(author.buildJpaEntity());
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDTO> getAll() {
        return authorRepository.getAll().stream()
                .map(entityConverter::buildDTO)
                .collect(Collectors.toList());
    }
}
