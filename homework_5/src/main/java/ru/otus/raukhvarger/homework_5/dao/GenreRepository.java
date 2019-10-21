package ru.otus.raukhvarger.homework_5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import java.util.*;
import java.util.function.Function;

@Service
public class GenreRepository implements IGenreRepository {

    private final RowMapper<GenreEntity> genreMapper;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public GenreRepository(RowMapper<GenreEntity> genreMapper, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.genreMapper = genreMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String selectWithJoin =
            "select b.id as b_id, b.id_genre, b.caption, g.id as g_id, g.genre \n" +
                "from genres_table g \n" +
            "left join books_table b \n" +
                "on g.id = b.id_genre \n";

    Function<List<GenreEntity>, List<GenreEntity>> groupByGenre = genres -> {
        HashMap<Long, GenreEntity> result = new HashMap<>();
        genres.forEach(g -> {
            if (result.get(g.getId()) != null) {
                result.get(g.getId()).getBooks().addAll(g.getBooks());
            } else {
                result.put(g.getId(), g);
            }
        });
        return new ArrayList<>(result.values());
    };

    @Override
    public List<GenreEntity> findAllByGenreLike(String genre) {
        return jdbcTemplate.query(selectWithJoin + "where genre like ?",
                new Object[] { "%" + genre + "%"},
                genreMapper
        );
    }

    @Override
    public List<GenreEntity> findAll() {
        return groupByGenre.apply(jdbcTemplate.query(selectWithJoin,
                genreMapper));
    }

    @Override
    public Long saveAndReturnId(GenreEntity genre) {
        Map<String, Object> params = new HashMap<>();
        params.put("genre", genre.getGenre());

        return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("genres_table")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params)
                .longValue();
    }

    @Override
    public Optional<GenreEntity> findOne(Long id) {
        try {
            return Optional.of(groupByGenre.apply(jdbcTemplate.query(selectWithJoin + "where g.id = ?",
                    new Object[]{id},
                    genreMapper)).get(0));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(GenreEntity genre) {
        jdbcTemplate.update("update genres_table set genre = ? where id = ?",
                new Object[]{genre.getGenre(), genre.getId()});
    }

    @Override
    public void delete(Long id) {
        namedParameterJdbcTemplate.update("delete from genres_table where id = :id",
                Collections.singletonMap("id", id));
    }

}
