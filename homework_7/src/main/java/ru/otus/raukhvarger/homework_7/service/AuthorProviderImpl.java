package ru.otus.raukhvarger.homework_7.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_7.dto.AuthorDTO;
import ru.otus.raukhvarger.homework_7.jpa.entity.Author;
import ru.otus.raukhvarger.homework_7.jpa.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorProviderImpl implements AuthorProvider {
    private final AuthorRepository authorRepository;

    public AuthorProviderImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void create(AuthorDTO author) {
        authorRepository.save(author.buildJpaEntity());
    }

    @Override
    public AuthorDTO getById(Integer id) {
        Author author = authorRepository.findById(id).orElse(null);
        return author != null ? author.buildDTO() : null;
    }

    @Override
    public AuthorDTO getByName(String name) {
        Author author = authorRepository.findByAuthorName(name).orElse(null);
        return author != null ? author.buildDTO() : null;
    }

    @Override
    @Transactional
    public AuthorDTO getByNameWithBooks(String name) {
        Author author = authorRepository.findByAuthorName(name != null ? name.toUpperCase() : null).orElse(null);
        AuthorDTO authorDTO = null;
        if (author != null) {
            authorDTO = author.buildDTO();
            authorDTO.setBooks(
                    author.getBooks().stream()
                            .map(b -> b.buildDTO())
                            .collect(Collectors.toSet())
            );
        }
        return authorDTO;
    }

    @Override
    public AuthorDTO getOrCreateByName(String name) {
        if (authorRepository.findByAuthorName(name) == null) authorRepository.save(
                AuthorDTO.builder()
                        .authorName(name)
                        .build()
                        .buildJpaEntity()
        );
        Author author = authorRepository.findByAuthorName(name).orElse(null);
        return author != null ? author.buildDTO() : null;
    }

    @Override
    public void update(AuthorDTO author) {
        authorRepository.save(author.buildJpaEntity());
    }

    @Override
    public void deleteById(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
                .map(a -> a.buildDTO())
                .collect(Collectors.toList());
    }
}
