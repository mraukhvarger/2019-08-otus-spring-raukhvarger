package ru.otus.raukhvarger.homework_5.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.raukhvarger.homework_5.entity.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test genre db provider")
@JdbcTest
@Import({Configuration.class})
public class GenreRepositoryImplTest {
    public static final String TEST_NEW_GENRE_NAME = "Стихи";
    public static final String TEST_EXISTING_GENRE_NAME = "Роман";
    public static final Integer TEST_EXISTING_GENRE_ID = 2;
    @Autowired
    private GenreRepositoryImpl genreRepositoryImpl;

    @Test
    @DisplayName("test create genre")
    void testCreateGenre() {
        Genre genre = new Genre(TEST_NEW_GENRE_NAME);
        genreRepositoryImpl.insertGenre(genre);
        Genre createdGenre = genreRepositoryImpl.getGenreByName(TEST_NEW_GENRE_NAME);
        assertNotNull(createdGenre.getGenreId());
        assertEquals(createdGenre.getGenreName(), genre.getGenreName());
    }

    @Test
    @DisplayName("test getting existing genre by id")
    void testGetExistingGenreById() {
        Genre dataGenre = genreRepositoryImpl.getExistingGenreById(TEST_EXISTING_GENRE_ID);
        assertNotNull(dataGenre);
        assertEquals(dataGenre.getGenreName(), TEST_EXISTING_GENRE_NAME);
    }

    @Test
    @DisplayName("test getting author by name")
    void testGetGenreByName() {
        Genre existingGenre = genreRepositoryImpl.getGenreByName(TEST_EXISTING_GENRE_NAME);
        assertNotNull(existingGenre.getGenreId());
        assertEquals(existingGenre.getGenreId(), TEST_EXISTING_GENRE_ID);
    }
}
