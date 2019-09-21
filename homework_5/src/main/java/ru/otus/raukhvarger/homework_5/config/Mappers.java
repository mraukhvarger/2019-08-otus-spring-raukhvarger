package ru.otus.raukhvarger.homework_5.config;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorAndBookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

public class Mappers {

    public static final RowMapper<AuthorEntity> authorMapper = (rs, i) -> new AuthorEntity(
            rs.getInt("id"),
            rs.getString("first_name"),
            rs.getString("last_name")
    );

    public static final RowMapper<BookEntity> bookMapper = (rs, i) -> new BookEntity(
            rs.getInt("id"),
            (Integer) rs.getObject("id_genre"),
            rs.getString("caption")
    );

    public static final RowMapper<GenreEntity> genreMapper = (rs, i) -> new GenreEntity(
            rs.getInt("id"),
            rs.getString("genre")
    );

    public static final RowMapper<AuthorAndBookEntity> authorAndBookMapper = (rs, i) -> new AuthorAndBookEntity(
            rs.getInt("id"),
            rs.getInt("id_author"),
            rs.getInt("id_book")
    );

}
