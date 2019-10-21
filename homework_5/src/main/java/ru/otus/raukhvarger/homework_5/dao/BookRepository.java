package ru.otus.raukhvarger.homework_5.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;

import java.util.*;
import java.util.function.Function;

@Service
public class BookRepository implements IBookRepository {

    private final RowMapper<BookEntity> bookMapper;
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public BookRepository(RowMapper<BookEntity> bookMapper, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.bookMapper = bookMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static final String selectWithJoin =
            "select a.id as a_id, a.first_name, a.last_name, b.id as b_id, b.id_genre, b.caption, g.id as g_id, g.genre \n" +
                "from books_table b \n" +
            "left join genres_table g \n" +
                "on g.id = b.id_genre \n" +
            "left join authors_and_books_table ab \n" +
                "on b.id = ab.id_book \n" +
            "left join authors_table a \n" +
                "on ab.id_author = a.id \n";

    Function<List<BookEntity>, List<BookEntity>> groupByBook = books -> {
        HashMap<Long, BookEntity> result = new HashMap<>();
        books.forEach(b -> {
            if (result.get(b.getId()) != null) {
                result.get(b.getId()).getAuthors().addAll(b.getAuthors());
            } else {
                result.put(b.getId(), b);
            }
        });
        return new ArrayList<>(result.values());
    };

    @Override
    public List<BookEntity> findAll() {
        return groupByBook.apply(jdbcTemplate.query(selectWithJoin,
                bookMapper));
    }

    @Override
    public List<BookEntity> findAllByCaptionLike(String caption) {
        return groupByBook.apply(jdbcTemplate.query(selectWithJoin + "where caption like ?",
                new Object[] { "%" + caption + "%"},
                bookMapper
        ));
    }

    @Override
    public List<BookEntity> findAllByGenreEq(Integer genreId) {
        return groupByBook.apply(jdbcTemplate.query(selectWithJoin + "where id_genre = ?",
                new Object[] { genreId },
                bookMapper
        ));
    }

    @Override
    public Long saveAndReturnId(BookEntity book) {
        Map<String, Object> params = new HashMap<>();
        try { params.put("id_genre", book.getGenre().getId()); } catch (Throwable ignore) { }
        params.put("caption", book.getCaption());

        return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName("books_table")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(params)
                .longValue();
    }

    @Override
    public Optional<BookEntity> findOne(Long id) {
        try {
            return Optional.of(groupByBook.apply(jdbcTemplate.query(selectWithJoin + "where b.id = ?",
                    new Object[]{id},
                    bookMapper)).get(0));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(BookEntity book) {
        Map<String, Object> params = new HashMap<>();
        params.put("idGenre", book.getGenre() != null ? book.getGenre().getId() : null);
        params.put("caption", book.getCaption());
        params.put("id", book.getId());

        namedParameterJdbcTemplate.update("update books_table set id_genre = :idGenre, caption = :caption where id = :id",
                params);
        book.getAuthors().forEach(a -> {
            params.clear();
            params.put("id_author", a.getId());
            params.put("id_book", book.getId());
            new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                    .withTableName("authors_and_books_table")
                    .execute(params);
        });
    }

    @Override
    public void delete(Long id) {
        namedParameterJdbcTemplate.update("delete from books_table where id = :id",
                Collections.singletonMap("id", id));
    }
}
