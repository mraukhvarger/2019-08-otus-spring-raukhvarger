package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_6.dto.BookDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.BookEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookImpl implements BookProvider {

    private final BookRepository bookRepository;

    public BookImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(BookDTO book) {
        bookRepository.insert(book.buildJpaEntity());
    }

    @Override
    public BookDTO getById(Integer id) {
        BookEntity bookEntity = bookRepository.getById(id);
        return bookEntity != null ? bookEntity.buildDTO() : null;
    }

    @Override
    public List<BookDTO> getByName(String name) {
        return bookRepository.getByName(name).stream()
                .map(a -> a.buildDTO())
                .collect(Collectors.toList());
    }

    @Override
    public void update(BookDTO book) {
        bookRepository.update(book.buildJpaEntity());
    }

    @Override
    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> getAll() {
        return bookRepository.getAll().stream()
                .map(a -> a.buildDTO())
                .collect(Collectors.toList());
    }
}
