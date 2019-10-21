package ru.otus.raukhvarger.homework_5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;

import java.util.*;
import java.util.function.Function;

@Service
public class AuthorRepositoryImpl implements AuthorRepository {

    private final RowMapper<AuthorEntity> authorMapper;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public AuthorRepositoryImpl(RowMapper<AuthorEntity> authorMapper, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.authorMapper = authorMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String selectWithJoin =
            "select a.id as a_id, a.first_name, a.last_name, b.id as b_id, b.id_genre, b.caption, g.id as g_id, g.genre \n" +
                "from authors_table a \n" +
            "left join authors_and_books_table ab \n" +
                "on ab.id_author = a.id \n" +
            "left join books_table b \n" +
                "on b.id = ab.id_book \n" +
            "left join genres_table g \n" +
                "on g.id = b.id_genre \n";

    Function<List<AuthorEntity>, List<AuthorEntity>> groupByAuthor = authors -> {
        HashMap<Long, AuthorEntity> result = new HashMap<>();
        authors.forEach(a -> {
            if (result.get(a.getId()) != null) {
                result.get(a.getId()).getBooks().addAll(a.getBooks());
            } else {
                result.put(a.getId(), a);
            }
        });
        return new ArrayList<>(result.values());
    };

    @Override
    public List<AuthorEntity> findAll() {
        return groupByAuthor.apply(jdbcTemplate.query(selectWithJoin,
                authorMapper));
    }

    @Override
    public List<AuthorEntity> findAllByFirstNameLike(String firstName) {
        return groupByAuthor.apply(jdbcTemplate.query(selectWithJoin + "where first_name like ?",
                new Object[] { "%" + firstName + "%"},
                authorMapper
        ));
    }

    @Override
    public List<AuthorEntity> findAllByLastNameLike(String lastName) {
        return groupByAuthor.apply(jdbcTemplate.query(selectWithJoin + "where last_name like ?",
                new Object[] { "%" + lastName + "%"},
                authorMapper
        ));
    }

    @Override
    public Long saveAndReturnId(AuthorEntity author) {
        Map<String, Object> params = new HashMap<>();
        params.put("first_name", author.getFirstName());
        params.put("last_name", author.getLastName());

        return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("authors_table")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params)
                .longValue();
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        try {
            return Optional.of(groupByAuthor.apply(jdbcTemplate.query(selectWithJoin + "where a.id = ?",
                    new Object[]{id},
                    authorMapper)).get(0));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(AuthorEntity author) {
        jdbcTemplate.update("update authors_table set first_name = ?, last_name = ? where id = ?",
                author.getFirstName(), author.getLastName(), author.getId());

        author.getBooks().forEach(b -> {
            Map params = new HashMap();
            params.put("id_author", author.getId());
            params.put("id_book", b.getId());
            new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                    .withTableName("authors_and_books_table")
                    .execute(params);
        });
    }

    @Override
    public void delete(Long id) {
        namedParameterJdbcTemplate.update("delete from authors_table where id = :id",
                Collections.singletonMap("id", id));
    }

}
