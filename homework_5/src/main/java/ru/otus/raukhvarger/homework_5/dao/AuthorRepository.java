package ru.otus.raukhvarger.homework_5.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.otus.raukhvarger.homework_5.config.Mappers;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;

import java.util.List;

public class AuthorRepository extends CommonRepository<AuthorEntity> implements IAuthorRepository {

    public AuthorRepository(Class type, JdbcTemplate jdbcTemplate) {
        super(type, jdbcTemplate);
    }

    @Override
    public List<AuthorEntity> findAllByFirstNameLike(String firstName) {
        return jdbcTemplate.query("select * from authors_table where first_name like ?",
                new Object[] { "%" + firstName + "%"},
                Mappers.authorMapper
        );
    }

    @Override
    public List<AuthorEntity> findAllByLastNameLike(String lastName) {
        return jdbcTemplate.query("select * from authors_table where last_name like ?",
                new Object[] { "%" + lastName + "%"},
                Mappers.authorMapper
        );
    }
}
