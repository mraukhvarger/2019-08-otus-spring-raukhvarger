package ru.otus.raukhvarger.homework_5.db;

import ru.otus.raukhvarger.homework_5.entity.BookEntity;

import java.util.List;

public interface BookRepository {
    BookEntity getBookById(Integer bookId);

    List<BookEntity> getBooksByName(String bookName);

    void insertBook(BookEntity bookEntity);

    void deleteBookById(Integer bookId);

    List<BookEntity> getAllBooks();

    void updateBookAuthor(Integer bookId, Integer authorId);

    void updateBookGenre(Integer bookId, Integer genreId);

    void updateBookName(Integer bookId, String bookName);
}
