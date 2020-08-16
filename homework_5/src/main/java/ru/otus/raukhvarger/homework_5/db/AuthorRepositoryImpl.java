package ru.otus.raukhvarger.homework_5.db;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.utils.EntityConverter;
import ru.otus.raukhvarger.homework_5.utils.mapper.AuthorRowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    private final NamedParameterJdbcTemplate template;

    public AuthorRepositoryImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Author getExistingAuthorById(Integer authorId) {
        String query = "select * from table_author where authorid = :authorId";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("authorId", authorId);
        return template.queryForObject(query, queryParams, new AuthorRowMapper());
    }

    @Override
    public Author getAuthorByName(String authorName) {
        String query = "select * from table_author where upper(authorname) = :authorName";
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("authorName", authorName.toUpperCase());
        List<Author> authorEntities = template.query(query, queryParams, new AuthorRowMapper());
        if (authorEntities.size() > 0) return authorEntities.get(0);
        else return null;
    }

    @Override
    public void insertAuthor(Author author) {
        String query = "insert into table_author(authorname) values (:authorName)";
        template.update(query, EntityConverter.convertAuthorToMap(author));
    }
}
