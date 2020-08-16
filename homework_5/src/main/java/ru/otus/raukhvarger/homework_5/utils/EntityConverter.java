package ru.otus.raukhvarger.homework_5.utils;

import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.entity.Genre;

import java.util.HashMap;
import java.util.Map;

public class EntityConverter {

    public static Map convertAuthorToMap(Author author) {
        Map authorMap = new HashMap<>();
        if (author.getAuthorId() != null) {
            authorMap.put("authorId", author.getAuthorId());
        }
        authorMap.put("authorName", author.getAuthorName());
        return authorMap;
    }

    public static Map convertBookToMap(Book book) {
        Map bookMap = new HashMap<>();
        if (book.getBookId() != null) {
            bookMap.put("bookId", book.getBookId());
        }
        bookMap.put("bookName", book.getBookName());
        bookMap.put("authorId", book.getAuthor().getAuthorId());
        bookMap.put("genreId", book.getGenre().getGenreId());
        return bookMap;
    }

    public static Map convertGenreToMap(Genre genre) {
        Map genreMap = new HashMap<>();
        if (genre.getGenreId() != null) {
            genreMap.put("genreId", genre.getGenreId());
        }
        genreMap.put("genreName", genre.getGenreName());
        return genreMap;
    }

}
