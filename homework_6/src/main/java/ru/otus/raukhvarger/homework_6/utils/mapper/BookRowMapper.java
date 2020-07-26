package ru.otus.raukhvarger.homework_6.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;
import ru.otus.raukhvarger.homework_6.dto.BookDTO;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<BookDTO> {
    @Override
    public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookDTO book = new BookDTO();
        book.setBookId(rs.getInt("bookid"));
        book.setBookName(rs.getString("bookname"));
        book.setAuthor(new AuthorDTO(rs.getInt("authorid"), rs.getString("authorname")));
        book.setGenre(new GenreDTO(rs.getInt("genreid"), rs.getString("genrename")));
        return book;
    }
}