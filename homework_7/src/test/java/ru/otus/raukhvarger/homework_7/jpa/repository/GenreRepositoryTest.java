package ru.otus.raukhvarger.homework_7.jpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_7.jpa.entity.Genre;
import ru.otus.raukhvarger.homework_7.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test genre repository")
@SpringBootTest(classes = Application.class)
public class GenreRepositoryTest {
    public static final String TEST_NEW_GENRE_NAME = "Стихи";
    public static final String TEST_EXISTING_GENRE_NAME = "Роман";
    public static final Integer TEST_EXISTING_GENRE_ID = 2;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    @DisplayName("test create genre")
    void testCreateGenre() {
        Genre genre = Genre.builder()
                .genreName(TEST_NEW_GENRE_NAME)
                .build();
        genreRepository.save(genre);
        Genre createdGenre = genreRepository.findByGenreName(TEST_NEW_GENRE_NAME).orElse(Genre.builder().build());
        assertNotNull(createdGenre.getGenreId());
        assertEquals(createdGenre.getGenreName(), genre.getGenreName());
    }

    @Test
    @DisplayName("test getting existing genre by id")
    void testGetExistingGenreById() {
        Genre dataGenre = genreRepository.findById(TEST_EXISTING_GENRE_ID).orElse(Genre.builder().build());
        assertNotNull(dataGenre);
        assertEquals(dataGenre.getGenreName(), TEST_EXISTING_GENRE_NAME);
    }

    @Test
    @DisplayName("test getting author by name")
    void testGetGenreByName() {
        Genre existingGenre = genreRepository.findByGenreName(TEST_EXISTING_GENRE_NAME).orElse(Genre.builder().build());
        assertNotNull(existingGenre.getGenreId());
        assertEquals(existingGenre.getGenreId(), TEST_EXISTING_GENRE_ID);
    }
}
