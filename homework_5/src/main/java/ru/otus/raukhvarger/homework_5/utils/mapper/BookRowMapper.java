package ru.otus.raukhvarger.homework_5.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.entity.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("bookid"));
        book.setBookName(rs.getString("bookname"));
        book.setAuthor(new Author(rs.getInt("authorid"), rs.getString("authorname")));
        book.setGenre(new Genre(rs.getInt("genreid"), rs.getString("genrename")));
        return book;
    }
}