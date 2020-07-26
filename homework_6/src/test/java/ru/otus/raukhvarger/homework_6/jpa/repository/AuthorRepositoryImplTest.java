package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_6.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test author repository")
@SpringBootTest(classes = Application.class)
public class AuthorRepositoryImplTest {
    public static final String TEST_NEW_AUTHOR_NAME = "Ирвин Шоу";
    public static final String TEST_EXISTING_AUTHOR_NAME = "Рэй Брэдбери";
    public static final Integer TEST_EXISTING_AUTHOR_ID = 1;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("test create author")
    void testCreateAuthor() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorName(TEST_NEW_AUTHOR_NAME);
        authorRepository.insert(authorEntity);
        AuthorEntity createdAuthorEntity = authorRepository.getByName(TEST_NEW_AUTHOR_NAME);
        assertNotNull(createdAuthorEntity.getAuthorId());
        assertEquals(createdAuthorEntity.getAuthorName(), authorEntity.getAuthorName());
    }

    @Test
    @DisplayName("test getting existing author by id")
    void testGetExistingAuthorById() {
        AuthorEntity dataAuthorEntity = authorRepository.getById(TEST_EXISTING_AUTHOR_ID);
        assertNotNull(dataAuthorEntity.getAuthorName());
        assertEquals(dataAuthorEntity.getAuthorName(), TEST_EXISTING_AUTHOR_NAME);
    }

    @Test
    @DisplayName("test getting author by name")
    void testGetAuthorByName() {
        AuthorEntity existingAuthorEntity = authorRepository.getByName(TEST_EXISTING_AUTHOR_NAME);
        assertNotNull(existingAuthorEntity.getAuthorId());
        assertEquals(existingAuthorEntity.getAuthorId(), TEST_EXISTING_AUTHOR_ID);
    }
}
