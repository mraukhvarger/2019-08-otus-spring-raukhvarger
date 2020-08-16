package ru.otus.raukhvarger.homework_5.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_5.entity.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setAuthorId(rs.getInt("authorId"));
        author.setAuthorName(rs.getString("authorName"));
        return author;
    }
}