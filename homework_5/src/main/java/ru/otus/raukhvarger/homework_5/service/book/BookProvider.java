package ru.otus.raukhvarger.homework_5.service.book;

import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entity.BookEntity;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;

import java.util.List;

public interface BookProvider {
    void createBook(BookEntity bookEntity);

    BookEntity getBookById(Integer bookId);

    List<BookEntity> getBooksByName(String bookName);

    List<BookEntity> listBooks();

    void deleteBookById(Integer bookId);

    void updateBook(Integer bookId, AuthorEntity authorEntity, GenreEntity genreEntity, String bookName);
}
