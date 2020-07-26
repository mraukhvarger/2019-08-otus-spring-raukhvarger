package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_5.db.AuthorRepositoryImpl;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test author db provider")
@SpringBootTest(classes = Application.class)
public class AuthorEntityRepositoryImplTest {
    public static final String TEST_NEW_AUTHOR_NAME = "Ирвин Шоу";
    public static final String TEST_EXISTING_AUTHOR_NAME = "Рэй Брэдбери";
    public static final Integer TEST_EXISTING_AUTHOR_ID = 1;
    @Autowired
    private AuthorRepositoryImpl authorRepositoryImpl;

    @Test
    @DisplayName("test create author")
    void testCreateAuthor() {
        AuthorEntity authorEntity = new AuthorEntity(TEST_NEW_AUTHOR_NAME);
        authorRepositoryImpl.insertAuthor(authorEntity);
        AuthorEntity createdAuthorEntity = authorRepositoryImpl.getAuthorByName(TEST_NEW_AUTHOR_NAME);
        assertNotNull(createdAuthorEntity.getAuthorId());
        assertEquals(createdAuthorEntity.getAuthorName(), authorEntity.getAuthorName());
    }

    @Test
    @DisplayName("test getting existing author by id")
    void testGetExistingAuthorById() {
        AuthorEntity dataAuthorEntity = authorRepositoryImpl.getExistingAuthorById(TEST_EXISTING_AUTHOR_ID);
        assertNotNull(dataAuthorEntity.getAuthorName());
        assertEquals(dataAuthorEntity.getAuthorName(), TEST_EXISTING_AUTHOR_NAME);
    }

    @Test
    @DisplayName("test getting author by name")
    void testGetAuthorByName() {
        AuthorEntity existingAuthorEntity = authorRepositoryImpl.getAuthorByName(TEST_EXISTING_AUTHOR_NAME);
        assertNotNull(existingAuthorEntity.getAuthorId());
        assertEquals(existingAuthorEntity.getAuthorId(), TEST_EXISTING_AUTHOR_ID);
    }
}
