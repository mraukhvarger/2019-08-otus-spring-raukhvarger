package ru.otus.raukhvarger.homework_6.utils.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreRowMapper implements RowMapper<GenreDTO> {
    @Override
    public GenreDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        GenreDTO genre = new GenreDTO();
        genre.setGenreId(rs.getInt("genreId"));
        genre.setGenreName(rs.getString("genreName"));
        return genre;
    }
}
