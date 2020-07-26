package ru.otus.raukhvarger.homework_6.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<AuthorDTO> {
    @Override
    public AuthorDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorDTO author = new AuthorDTO();
        author.setAuthorId(rs.getInt("authorId"));
        author.setAuthorName(rs.getString("authorName"));
        return author;
    }
}