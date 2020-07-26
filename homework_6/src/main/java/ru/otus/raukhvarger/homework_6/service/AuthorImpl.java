package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorImpl implements AuthorProvider {
    private final AuthorRepository authorRepository;

    public AuthorImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(AuthorDTO author) {
        authorRepository.insert(author.buildJpaEntity());
    }

    @Override
    public AuthorDTO getById(Integer id) {
        AuthorEntity authorEntity = authorRepository.getById(id);
        return authorEntity != null ? authorEntity.buildDTO() : null;
    }

    @Override
    public AuthorDTO getByName(String name) {
        AuthorEntity authorEntity = authorRepository.getByName(name);
        return authorEntity != null ? authorEntity.buildDTO() : null;
    }

    @Override
    @Transactional
    public AuthorDTO getByNameWithBooks(String name) {
        AuthorEntity authorEntity = authorRepository.getByName(name != null ? name.toUpperCase() : null);
        AuthorDTO authorDTO = null;
        if (authorEntity != null) {
            authorDTO = authorEntity.buildDTO();
            authorDTO.setBooks(
                    authorEntity.getBookEntities().stream()
                            .map(b -> b.buildDTO())
                            .collect(Collectors.toSet())
            );
        }
        return authorDTO;
    }

    @Override
    public AuthorDTO getOrCreateByName(String name) {
        if (authorRepository.getByName(name) == null) authorRepository.insert((new AuthorDTO(name)).buildJpaEntity());
        return authorRepository.getByName(name).buildDTO();
    }

    @Override
    public void update(AuthorDTO author) {
        authorRepository.update(author.buildJpaEntity());
    }

    @Override
    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDTO> getAll() {
        return authorRepository.getAll().stream()
                .map(a -> a.buildDTO())
                .collect(Collectors.toList());
    }
}
