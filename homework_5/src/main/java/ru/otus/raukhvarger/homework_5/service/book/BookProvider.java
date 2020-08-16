package ru.otus.raukhvarger.homework_5.service.book;

import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.entity.Genre;

import java.util.List;

public interface BookProvider {
    void createBook(Book book);

    Book getBookById(Integer bookId);

    List<Book> getBooksByName(String bookName);

    List<Book> listBooks();

    void deleteBookById(Integer bookId);

    void updateBook(Integer bookId, Author author, Genre genre, String bookName);
}
