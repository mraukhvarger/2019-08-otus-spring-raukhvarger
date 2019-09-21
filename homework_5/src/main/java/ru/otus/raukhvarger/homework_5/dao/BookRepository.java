package ru.otus.raukhvarger.homework_5.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.otus.raukhvarger.homework_5.config.Mappers;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;

import java.util.List;

public class BookRepository extends CommonRepository<BookEntity> implements IBookRepository {

    public BookRepository(Class type, JdbcTemplate jdbcTemplate) {
        super(type, jdbcTemplate);
    }

    @Override
    public List<BookEntity> findAllByCaptionLike(String caption) {
        return jdbcTemplate.query("select * from books_table where caption like ?",
                new Object[] { "%" + caption + "%"},
                Mappers.bookMapper
        );
    }

    @Override
    public List<BookEntity> findAllByGenreEq(Integer genreId) {
        return jdbcTemplate.query("select * from books_table where id_genre = ?",
                new Object[] { genreId },
                Mappers.bookMapper
        );
    }
}
