package ru.otus.raukhvarger.homework_5.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorRowMapper implements RowMapper<AuthorEntity> {
    @Override
    public AuthorEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorId(rs.getInt("authorId"));
        authorEntity.setAuthorName(rs.getString("authorName"));
        return authorEntity;
    }
}