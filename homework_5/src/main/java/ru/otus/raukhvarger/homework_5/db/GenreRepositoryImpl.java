package ru.otus.raukhvarger.homework_5.db;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;
import ru.otus.raukhvarger.homework_5.utils.mapper.GenreRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    private final NamedParameterJdbcTemplate template;

    public GenreRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public GenreEntity getExistingGenreById(Integer genreId) {
        String query = "select * from table_genre where genreid = :genreId";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("genreId", genreId);
        return template.queryForObject(query, queryParams, new GenreRowMapper());
    }

    @Override
    public GenreEntity getGenreByName(String genreName) {
        String query = "select * from table_genre where upper(genrename) = :genreName";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("genreName", genreName.toUpperCase());
        List<GenreEntity> genreEntities = template.query(query, queryParams, new GenreRowMapper());
        if (genreEntities.size() > 0) return genreEntities.get(0);
        else return null;
    }

    @Override
    public void insertGenre(GenreEntity genreEntity) {
        String query = "insert into table_genre(genrename) values (:genreName)";
        template.execute(query, genreEntity.convertToMap(), (PreparedStatementCallback) ps -> ps.executeUpdate());
    }
}
