package ru.otus.raukhvarger.homework_5.service.book;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.db.BookRepository;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entity.BookEntity;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;

import java.util.List;

@Service
public class BookImpl implements BookProvider {

    private final BookRepository dbBookProvider;

    public BookImpl(BookRepository dbBookProvider) {
        this.dbBookProvider = dbBookProvider;
    }

    @Override
    public void createBook(BookEntity bookEntity) {
        dbBookProvider.insertBook(bookEntity);
    }

    @Override
    public List<BookEntity> getBooksByName(String bookName) {
        return dbBookProvider.getBooksByName(bookName);
    }

    @Override
    public BookEntity getBookById(Integer bookId) {
        return dbBookProvider.getBookById(bookId);
    }

    @Override
    public void deleteBookById(Integer bookId) {
        dbBookProvider.deleteBookById(bookId);
    }

    @Override
    public List<BookEntity> listBooks() {
        return dbBookProvider.getAllBooks();
    }

    @Override
    public void updateBook(Integer bookId, AuthorEntity authorEntity, GenreEntity genreEntity, String bookName) {
        if (authorEntity != null) dbBookProvider.updateBookAuthor(bookId, authorEntity.getAuthorId());
        if (genreEntity != null) dbBookProvider.updateBookGenre(bookId, genreEntity.getGenreId());
        if (bookName != null) dbBookProvider.updateBookName(bookId, bookName);
    }
}
