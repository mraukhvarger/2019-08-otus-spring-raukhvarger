package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.dto.BookDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.BookEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.BookRepository;
import ru.otus.raukhvarger.homework_6.utils.EntityConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookImpl implements BookProvider {

    private final BookRepository bookRepository;
    private final EntityConverter entityConverter;

    public BookImpl(BookRepository bookRepository, EntityConverter entityConverter) {
        this.bookRepository = bookRepository;
        this.entityConverter = entityConverter;
    }

    @Override
    public void create(BookDTO book) {
        bookRepository.insert(book.buildJpaEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO getById(Long id) {
        BookEntity bookEntity = bookRepository.getById(id);
        return bookEntity != null ? entityConverter.buildDTO(bookEntity) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getByName(String name) {
        return bookRepository.getByName(name).stream()
                .map(entityConverter::buildDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BookDTO book) {
        bookRepository.update(book.buildJpaEntity());
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAll() {
        return bookRepository.getAll().stream()
                .map(entityConverter::buildDTO)
                .collect(Collectors.toList());
    }
}
