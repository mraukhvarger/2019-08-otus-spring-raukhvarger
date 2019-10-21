package ru.otus.raukhvarger.homework_5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Configuration
public class Config {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(jdbcTemplate);
    }

    @Bean
    public RowMapper<AuthorEntity> authorMapper() {
        return (rs, i) -> {
            AuthorEntity author = AuthorEntity.builder()
                    .id(rs.getLong("a_id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .books(new ArrayList<>())
                    .build();
            if (rs.getObject("b_id") != null) {
                BookEntity book = BookEntity.builder()
                        .id(rs.getLong("b_id"))
                        .caption(rs.getString("caption"))
                        .authors(Collections.singletonList(author))
                        .build();
                if (rs.getObject("g_id") != null) {
                    GenreEntity genre = GenreEntity.builder()
                            .id(rs.getLong("g_id"))
                            .genre(rs.getString("genre"))
                            .build();
                    book.setGenre(genre);
                }
                author.getBooks().add(book);
            }
            return author;
        };
    }

    @Bean
    public RowMapper<BookEntity> bookMapper() {
        return (rs, i) -> {
            BookEntity book = BookEntity.builder()
                    .id(rs.getLong("b_id"))
                    .caption(rs.getString("caption"))
                    .authors(new ArrayList<>())
                    .build();
            if (rs.getObject("g_id") != null) {
                GenreEntity genre = GenreEntity.builder()
                        .id(rs.getLong("g_id"))
                        .genre(rs.getString("genre"))
                        .build();
                book.setGenre(genre);
            }
            if (rs.getObject("a_id") != null) {
                AuthorEntity author = AuthorEntity.builder()
                    .id(rs.getLong("a_id"))
                    .firstName(rs.getString("first_name"))
                    .lastName(rs.getString("last_name"))
                    .books(Collections.singletonList(book))
                    .build();
                book.getAuthors().add(author);
            }
            return book;
        };
    }

    @Bean
    public RowMapper<GenreEntity> genreMapper() {
        return (rs, i) -> {
            GenreEntity genre = GenreEntity.builder()
                    .id(rs.getLong("g_id"))
                    .genre(rs.getString("genre"))
                    .books(new ArrayList<>())
                    .build();
            if (rs.getObject("b_id") != null) {
                BookEntity book = BookEntity.builder()
                        .id(rs.getLong("b_id"))
                        .caption(rs.getString("caption"))
                        .genre(genre)
                        .authors(new ArrayList<>())
                        .build();
                genre.getBooks().add(book);
            }
            return genre;
        };
    }

}
