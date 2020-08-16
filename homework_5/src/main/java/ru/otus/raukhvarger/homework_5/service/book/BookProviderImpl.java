package ru.otus.raukhvarger.homework_5.service.book;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.db.BookRepository;
import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.entity.Genre;

import java.util.List;

@Service
public class BookProviderImpl implements BookProvider {

    private final BookRepository dbBookProvider;

    public BookProviderImpl(BookRepository dbBookProvider) {
        this.dbBookProvider = dbBookProvider;
    }

    @Override
    public void createBook(Book book) {
        dbBookProvider.insertBook(book);
    }

    @Override
    public List<Book> getBooksByName(String bookName) {
        return dbBookProvider.getBooksByName(bookName);
    }

    @Override
    public Book getBookById(Integer bookId) {
        return dbBookProvider.getBookById(bookId);
    }

    @Override
    public void deleteBookById(Integer bookId) {
        dbBookProvider.deleteBookById(bookId);
    }

    @Override
    public List<Book> listBooks() {
        return dbBookProvider.getAllBooks();
    }

    @Override
    public void updateBook(Integer bookId, Author author, Genre genre, String bookName) {
        if (author != null) dbBookProvider.updateBookAuthor(bookId, author.getAuthorId());
        if (genre != null) dbBookProvider.updateBookGenre(bookId, genre.getGenreId());
        if (bookName != null) dbBookProvider.updateBookName(bookId, bookName);
    }
}
