package ru.otus.raukhvarger.homework_5.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.raukhvarger.homework_5.entity.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test author db provider")
@JdbcTest
@Import({Configuration.class})
public class AuthorRepositoryImplTest {
    public static final String TEST_NEW_AUTHOR_NAME = "Ирвин Шоу";
    public static final String TEST_EXISTING_AUTHOR_NAME = "Рэй Брэдбери";
    public static final Integer TEST_EXISTING_AUTHOR_ID = 1;
    @Autowired
    private AuthorRepositoryImpl authorRepositoryImpl;

    @Test
    @DisplayName("test create author")
    void testCreateAuthor() {
        Author author = new Author(TEST_NEW_AUTHOR_NAME);
        authorRepositoryImpl.insertAuthor(author);
        Author createdAuthor = authorRepositoryImpl.getAuthorByName(TEST_NEW_AUTHOR_NAME);
        assertNotNull(createdAuthor.getAuthorId());
        assertEquals(createdAuthor.getAuthorName(), author.getAuthorName());
    }

    @Test
    @DisplayName("test getting existing author by id")
    void testGetExistingAuthorById() {
        Author dataAuthor = authorRepositoryImpl.getExistingAuthorById(TEST_EXISTING_AUTHOR_ID);
        assertNotNull(dataAuthor.getAuthorName());
        assertEquals(dataAuthor.getAuthorName(), TEST_EXISTING_AUTHOR_NAME);
    }

    @Test
    @DisplayName("test getting author by name")
    void testGetAuthorByName() {
        Author existingAuthor = authorRepositoryImpl.getAuthorByName(TEST_EXISTING_AUTHOR_NAME);
        assertNotNull(existingAuthor.getAuthorId());
        assertEquals(existingAuthor.getAuthorId(), TEST_EXISTING_AUTHOR_ID);
    }
}
