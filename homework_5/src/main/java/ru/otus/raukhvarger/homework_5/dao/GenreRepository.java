package ru.otus.raukhvarger.homework_5.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.otus.raukhvarger.homework_5.config.Mappers;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import java.util.List;

public class GenreRepository extends CommonRepository<GenreEntity> implements IGenreRepository {

    public GenreRepository(Class type, JdbcTemplate jdbcTemplate) {
        super(type, jdbcTemplate);
    }

    @Override
    public List<GenreEntity> findAllByGenreLike(String genre) {
        return jdbcTemplate.query("select * from genres_table where genre like ?",
                new Object[] { "%" + genre + "%"},
                Mappers.genreMapper
        );
    }
}
