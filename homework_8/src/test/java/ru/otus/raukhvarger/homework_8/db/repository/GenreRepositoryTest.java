package ru.otus.raukhvarger.homework_8.db.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_8.db.entity.Genre;
import ru.otus.raukhvarger.homework_8.db.repository.GenreRepository;
import ru.otus.raukhvarger.homework_8.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test genre repository")
@SpringBootTest(classes = Application.class)
public class GenreRepositoryTest {
    public static final String TEST_NEW_GENRE_NAME = "Poems";
    public static final String TEST_EXISTING_GENRE_NAME = "Science fiction";
    public static final String TEST_EXISTING_GENRE_ID = "046655eb-d84d-424c-8e6d-05b47041d30b";
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("test create genre")
    void testCreateGenre() {
        Genre genre = Genre.builder()
                .genreName(TEST_NEW_GENRE_NAME)
                .build();
        genreRepository.save(genre);
        Genre createdGenre= genreRepository.findByGenreName(TEST_NEW_GENRE_NAME).orElse(Genre.builder().build());
        assertNotNull(createdGenre.getGenreId());
        assertEquals(createdGenre.getGenreName(), genre.getGenreName());
    }

    @Test
    @DisplayName("test getting existing genre by genre id")
    void testGetExistingGenreById() {
        Genre dataGenre = genreRepository.findById(TEST_EXISTING_GENRE_ID).orElse(Genre.builder().build());
        assertNotNull(dataGenre);
        assertEquals(dataGenre.getGenreName(), TEST_EXISTING_GENRE_NAME);
    }

    @Test
    @DisplayName("test getting genre by genre name")
    void testGetGenreByName() {
        Genre existingGenre = genreRepository.findByGenreName(TEST_EXISTING_GENRE_NAME).orElse(Genre.builder().build());
        assertNotNull(existingGenre.getGenreId());
        assertEquals(existingGenre.getGenreId(), TEST_EXISTING_GENRE_ID);
    }
}
