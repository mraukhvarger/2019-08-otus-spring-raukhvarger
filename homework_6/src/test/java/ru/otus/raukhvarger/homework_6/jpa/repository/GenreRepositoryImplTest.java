package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;
import ru.otus.raukhvarger.homework_6.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test genre repository")
@SpringBootTest(classes = Application.class)
public class GenreRepositoryImplTest {
    public static final String TEST_NEW_GENRE_NAME = "Стихи";
    public static final String TEST_EXISTING_GENRE_NAME = "Роман";
    public static final Integer TEST_EXISTING_GENRE_ID = 2;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("test create genre")
    void testCreateGenre() {
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenreName(TEST_NEW_GENRE_NAME);
        genreRepository.insert(genreEntity);
        GenreEntity createdGenreEntity = genreRepository.getByName(TEST_NEW_GENRE_NAME);
        assertNotNull(createdGenreEntity.getGenreId());
        assertEquals(createdGenreEntity.getGenreName(), genreEntity.getGenreName());
    }

    @Test
    @DisplayName("test getting existing genre by id")
    void testGetExistingGenreById() {
        GenreEntity dataGenreEntity = genreRepository.getById(TEST_EXISTING_GENRE_ID);
        assertNotNull(dataGenreEntity);
        assertEquals(dataGenreEntity.getGenreName(), TEST_EXISTING_GENRE_NAME);
    }

    @Test
    @DisplayName("test getting author by name")
    void testGetGenreByName() {
        GenreEntity existingGenreEntity = genreRepository.getByName(TEST_EXISTING_GENRE_NAME);
        assertNotNull(existingGenreEntity.getGenreId());
        assertEquals(existingGenreEntity.getGenreId(), TEST_EXISTING_GENRE_ID);
    }
}
