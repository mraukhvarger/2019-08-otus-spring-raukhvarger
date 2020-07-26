package ru.otus.raukhvarger.homework_5.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<GenreEntity> {
    @Override
    public GenreEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenreId(rs.getInt("genreId"));
        genreEntity.setGenreName(rs.getString("genreName"));
        return genreEntity;
    }
}
