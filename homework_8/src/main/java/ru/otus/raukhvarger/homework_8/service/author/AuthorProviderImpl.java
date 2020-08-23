package ru.otus.raukhvarger.homework_8.service.author;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_8.db.entity.Author;
import ru.otus.raukhvarger.homework_8.db.repository.AuthorRepository;
import ru.otus.raukhvarger.homework_8.db.repository.BookRepository;
import ru.otus.raukhvarger.homework_8.dto.AuthorDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthorProviderImpl implements AuthorProvider {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorProviderImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(AuthorDTO author) {
        authorRepository.save(author.buildDBEntity());
    }

    @Override
    public AuthorDTO getById(String id) {
        Author author = authorRepository.findById(id).orElse(null);
        return author != null ? author.buildDTO() : null;
    }

    @Override
    public AuthorDTO getByName(String name) {
        Author author = authorRepository.findByAuthorNameIgnoreCase(name).orElse(null);
        return author != null ? author.buildDTO() : null;
    }

    @Override
    @Transactional
    public AuthorDTO getByNameWithBooks(String name) {
        Author author = authorRepository.findByAuthorNameIgnoreCase(name).orElse(null);
        AuthorDTO authorDTO = null;
        if (author != null) {
            authorDTO = author.buildDTO();
            authorDTO.setBooks(
                    bookRepository.findAllByAuthorAuthorId(author.getAuthorId()).stream()
                            .map(b -> b.buildDTO())
                            .collect(Collectors.toSet())
            );
        }
        return authorDTO;
    }

    @Override
    public AuthorDTO getOrCreateByName(String name) {
        if (authorRepository.findByAuthorNameIgnoreCase(name).orElse(null) == null) authorRepository.save(
                AuthorDTO.builder()
                        .authorId(UUID.randomUUID().toString())
                        .authorName(name)
                        .build()
                        .buildDBEntity()
        );
        Author author = authorRepository.findByAuthorNameIgnoreCase(name).orElse(null);
        return author != null ? author.buildDTO() : null;
    }

    @Override
    public void update(AuthorDTO author) {
        authorRepository.save(author.buildDBEntity());
    }

    @Override
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
                .map(a -> a.buildDTO())
                .collect(Collectors.toList());
    }
}
