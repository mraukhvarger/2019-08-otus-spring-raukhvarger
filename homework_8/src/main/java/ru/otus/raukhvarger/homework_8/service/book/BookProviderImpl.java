package ru.otus.raukhvarger.homework_8.service.book;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_8.db.repository.CommentRepository;
import ru.otus.raukhvarger.homework_8.dto.BookDTO;
import ru.otus.raukhvarger.homework_8.db.entity.Book;
import ru.otus.raukhvarger.homework_8.db.repository.BookRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookProviderImpl implements BookProvider {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    public BookProviderImpl(BookRepository bookRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public void create(BookDTO book) {
        bookRepository.save(book.buildDBEntity());
    }

    @Override
    public BookDTO getById(String id) {
        Book book = bookRepository.findById(id).orElse(null);
        return book != null ? book.buildDTO() : null;
    }

    @Override
    public List<BookDTO> getByName(String name) {
        return bookRepository.findByBookName(name).stream()
                .map(a -> a.buildDTO())
                .collect(Collectors.toList());
    }

    @Override
    public void update(BookDTO book) {
        bookRepository.save(book.buildDBEntity());
    }

    @Override
    public void deleteById(String id) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) commentRepository.deleteAll(book.getComments());
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(a -> a.buildDTO())
                .collect(Collectors.toList());
    }
}
