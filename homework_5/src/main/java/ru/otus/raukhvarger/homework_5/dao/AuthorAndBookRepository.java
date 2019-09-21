package ru.otus.raukhvarger.homework_5.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.otus.raukhvarger.homework_5.config.Mappers;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorAndBookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AuthorAndBookRepository extends CommonRepository<AuthorAndBookEntity> implements IAuthorAndBookRepository {

    public AuthorAndBookRepository(Class type, JdbcTemplate jdbcTemplate) {
        super(type, jdbcTemplate);
    }

    @Override
    public void save(AuthorEntity author, BookEntity book) {
        save(new AuthorAndBookEntity(author.getId(), book.getId()));
    }

    @Override
    public void save(Integer authorId, Integer bookId) {
        save(new AuthorAndBookEntity(authorId, bookId));
    }

    @Override
    public void delete(AuthorEntity author, BookEntity book) {
        delete(author.getId(), book.getId());
    }

    @Override
    public void delete(Integer authorId, Integer bookId) {
        jdbcTemplate.update("delete from authors_and_books_table where id_author = ? and id_book = ?",
                authorId, bookId);
    }

    @Override
    public List<BookEntity> findAllBooksByAuthorId(Integer authorId) {
        List<Integer> bookIds = jdbcTemplate.query("select * from authors_and_books_table where id_author = ?",
                new Object[] { authorId },
                Mappers.authorAndBookMapper
        ).stream().map(AuthorAndBookEntity::getIdBook).collect(Collectors.toList());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        return namedParameterJdbcTemplate.query("select * from books_table where id in (:ids)",
                Collections.singletonMap("ids", bookIds),
                Mappers.bookMapper
        );
    }

    @Override
    public List<AuthorEntity> findAllAuthorsByBookId(Integer bookId) {
        List<Integer> authorIds = jdbcTemplate.query("select * from authors_and_books_table where id_book = ?",
                new Object[] { bookId },
                Mappers.authorAndBookMapper
        ).stream().map(AuthorAndBookEntity::getIdAuthor).collect(Collectors.toList());

        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                new NamedParameterJdbcTemplate(jdbcTemplate.getDataSource());

        return namedParameterJdbcTemplate.query("select * from authors_table where id in (:ids)",
                Collections.singletonMap("ids", authorIds),
                Mappers.authorMapper
        );
    }
}
