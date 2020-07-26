package ru.otus.raukhvarger.homework_5.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entity.BookEntity;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<BookEntity> {
    @Override
    public BookEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(rs.getInt("bookid"));
        bookEntity.setBookName(rs.getString("bookname"));
        bookEntity.setAuthorEntity(new AuthorEntity(rs.getInt("authorid"), rs.getString("authorname")));
        bookEntity.setGenreEntity(new GenreEntity(rs.getInt("genreid"), rs.getString("genrename")));
        return bookEntity;
    }
}