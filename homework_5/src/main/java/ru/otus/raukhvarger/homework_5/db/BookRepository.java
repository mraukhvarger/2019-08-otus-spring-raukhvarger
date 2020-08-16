package ru.otus.raukhvarger.homework_5.db;

import ru.otus.raukhvarger.homework_5.entity.Book;

import java.util.List;

public interface BookRepository {
    Book getBookById(Integer bookId);

    List<Book> getBooksByName(String bookName);

    void insertBook(Book book);

    void deleteBookById(Integer bookId);

    List<Book> getAllBooks();

    void updateBookAuthor(Integer bookId, Integer authorId);

    void updateBookGenre(Integer bookId, Integer genreId);

    void updateBookName(Integer bookId, String bookName);
}
