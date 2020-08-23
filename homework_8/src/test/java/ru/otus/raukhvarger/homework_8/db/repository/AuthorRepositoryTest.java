package ru.otus.raukhvarger.homework_8.db.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.raukhvarger.homework_8.db.entity.Author;
import ru.otus.raukhvarger.homework_8.db.repository.AuthorRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test author repository")
@SpringBootConfiguration
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.raukhvarger.homework_8"})
public class AuthorRepositoryTest {
    public static final String TEST_NEW_AUTHOR_NAME = "Irwin Shaw";

    public static final String TEST_EXISTING_AUTHOR_NAME = "Ray Douglas Bradbury";
    public static final String TEST_EXISTING_AUTHOR_ID = "0057666e-25c2-44f9-a76a-c1ca42e1ba2f";
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("test create author")
    void testCreateAuthor() {
        Author author = Author.builder()
                .authorName(TEST_NEW_AUTHOR_NAME)
                .build();
        author.setAuthorName(TEST_NEW_AUTHOR_NAME);
        authorRepository.save(author);
        Author createdAuthor = authorRepository.findByAuthorNameIgnoreCase(TEST_NEW_AUTHOR_NAME).orElse(Author.builder().build());
        assertNotNull(createdAuthor.getAuthorId());
        assertEquals(createdAuthor.getAuthorName(), author.getAuthorName());
    }

    @Test
    @DisplayName("test getting existing author by bookId")
    void testGetExistingAuthorById() {
        Author dataAuthor = authorRepository.findById(TEST_EXISTING_AUTHOR_ID).orElse(Author.builder().build());
        assertNotNull(dataAuthor.getAuthorName());
        assertEquals(dataAuthor.getAuthorName(), TEST_EXISTING_AUTHOR_NAME);
    }

    @Test
    @DisplayName("test getting author by genreName")
    void testGetAuthorByName() {
        Author existingAuthor = authorRepository.findByAuthorNameIgnoreCase(TEST_EXISTING_AUTHOR_NAME).orElse(Author.builder().build());
        assertNotNull(existingAuthor.getAuthorId());
        assertEquals(existingAuthor.getAuthorId(), TEST_EXISTING_AUTHOR_ID);
    }
}
