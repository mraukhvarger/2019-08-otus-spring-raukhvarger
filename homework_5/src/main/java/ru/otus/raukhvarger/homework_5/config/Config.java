package ru.otus.raukhvarger.homework_5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.otus.raukhvarger.homework_5.dao.*;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorAndBookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

@Configuration
public class Config {

    @Bean
    @DependsOn("initSQL")
    IBookRepository getBookRepository(JdbcTemplate jdbcTemplate) {
        return new BookRepository(BookEntity.class, jdbcTemplate);
    }

    @Bean
    @DependsOn("initSQL")
    IAuthorRepository getAuthorRepository(JdbcTemplate jdbcTemplate) {
        return new AuthorRepository(AuthorEntity.class, jdbcTemplate);
    }

    @Bean
    @DependsOn("initSQL")
    IGenreRepository getGenreRepository(JdbcTemplate jdbcTemplate) {
        return new GenreRepository(GenreEntity.class, jdbcTemplate);
    }

    @Bean
    @DependsOn("initSQL")
    IAuthorAndBookRepository getAuthorAndBookRepository(JdbcTemplate jdbcTemplate) {
        return new AuthorAndBookRepository(AuthorAndBookEntity.class, jdbcTemplate);
    }

}
