package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_5.db.GenreRepositoryImpl;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;
import ru.otus.raukhvarger.homework_5.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test genre db provider")
@SpringBootTest(classes = Application.class)
public class GenreEntityRepositoryImplTest {
    public static final String TEST_NEW_GENRE_NAME = "Стихи";
    public static final String TEST_EXISTING_GENRE_NAME = "Роман";
    public static final Integer TEST_EXISTING_GENRE_ID = 2;
    @Autowired
    private GenreRepositoryImpl genreRepositoryImpl;

    @Test
    @DisplayName("test create genre")
    void testCreateGenre() {
        GenreEntity genreEntity = new GenreEntity(TEST_NEW_GENRE_NAME);
        genreRepositoryImpl.insertGenre(genreEntity);
        GenreEntity createdGenreEntity = genreRepositoryImpl.getGenreByName(TEST_NEW_GENRE_NAME);
        assertNotNull(createdGenreEntity.getGenreId());
        assertEquals(createdGenreEntity.getGenreName(), genreEntity.getGenreName());
    }

    @Test
    @DisplayName("test getting existing genre by id")
    void testGetExistingGenreById() {
        GenreEntity dataGenreEntity = genreRepositoryImpl.getExistingGenreById(TEST_EXISTING_GENRE_ID);
        assertNotNull(dataGenreEntity);
        assertEquals(dataGenreEntity.getGenreName(), TEST_EXISTING_GENRE_NAME);
    }

    @Test
    @DisplayName("test getting author by name")
    void testGetGenreByName() {
        GenreEntity existingGenreEntity = genreRepositoryImpl.getGenreByName(TEST_EXISTING_GENRE_NAME);
        assertNotNull(existingGenreEntity.getGenreId());
        assertEquals(existingGenreEntity.getGenreId(), TEST_EXISTING_GENRE_ID);
    }
}
