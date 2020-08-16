package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_6.jpa.entity.BookEntity;
import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;
import ru.otus.raukhvarger.homework_6.spring.Application;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test book repository")
@DataJpaTest
@Import(Configuration.class)
@Transactional
public class BookRepositoryImplTest {
    public static final String TEST_NEW_BOOK_NAME = "Чума";
    public static final String TEST_NEW_AUTHOR_NAME = "Альбер Камю";
    public static final String TEST_NEW_GENRE_NAME = "Философский роман";
    public static final Long TEST_NEW_BOOK_ID = 6L;

    public static final Integer TEST_EXPECTED_BOOKS_SIZE = 5;

    public static final String TEST_EXISTING_BOOK_NAME = "451 градус по фаренгейту";
    public static final Long TEST_EXISTING_BOOK_ID = 1L;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("test create book")
    void testCreateBook() {
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorName(TEST_NEW_AUTHOR_NAME);
        authorRepository.insert(authorEntity);
        AuthorEntity createdAuthorEntity = authorRepository.getByName(TEST_NEW_AUTHOR_NAME);

        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setGenreName(TEST_NEW_GENRE_NAME);
        genreRepository.insert(genreEntity);
        GenreEntity createdGenreEntity = genreRepository.getByName(TEST_NEW_GENRE_NAME);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookName(TEST_NEW_BOOK_NAME);
        bookEntity.setGenreId(createdGenreEntity.getGenreId());
        bookEntity.setAuthorId(createdAuthorEntity.getAuthorId());
        bookRepository.insert(bookEntity);

        BookEntity createdBookEntity = bookRepository.getById(TEST_NEW_BOOK_ID);
        assertNotNull(createdBookEntity);
        assertEquals(TEST_NEW_BOOK_NAME, createdBookEntity.getBookName());
        assertEquals(createdAuthorEntity.getAuthorId(), createdBookEntity.getAuthorId());
        assertEquals(createdGenreEntity.getGenreId(), createdBookEntity.getGenreId());
    }

    @Test
    @DisplayName("test delete book by id")
    void testDeleteBookById() {
        bookRepository.deleteById(TEST_EXISTING_BOOK_ID);
        BookEntity existingBookEntity = bookRepository.getById(TEST_EXISTING_BOOK_ID);
        assertNull(existingBookEntity);
    }

    @Test
    @DisplayName("test getting book by id")
    void testGetBookById() {
        BookEntity dataBookEntity = bookRepository.getById(TEST_EXISTING_BOOK_ID);
        assertNotNull(dataBookEntity);
        assertEquals(dataBookEntity.getBookName(), TEST_EXISTING_BOOK_NAME);
    }

    @Test
    @DisplayName("test getting book by name")
    void testGetBookByName() {
        List<BookEntity> existingBookEntities = bookRepository.getByName(TEST_EXISTING_BOOK_NAME);
        assertNotNull(existingBookEntities.get(0));
        BookEntity existingBookEntity = existingBookEntities.get(0);
        assertNotNull(existingBookEntity.getBookId());
        assertEquals(existingBookEntity.getBookId(), TEST_EXISTING_BOOK_ID);
    }

    @Test
    @DisplayName("test get all books")
    void testGetAllBooks() {
        List<BookEntity> bookEntities = bookRepository.getAll();
        assertThat(bookEntities.size()).isGreaterThan(1);
        assertThat(bookEntities.size()).isEqualTo(TEST_EXPECTED_BOOKS_SIZE);
    }

    @Test
    @DisplayName("test update book")
    void testUpdateBook() {
        BookEntity bookEntity = bookRepository.getById(TEST_EXISTING_BOOK_ID);
        bookEntity.setAuthorId(2L);
        bookEntity.setGenreId(2L);
        bookEntity.setBookName(TEST_NEW_BOOK_NAME);
        bookRepository.update(bookEntity);
        BookEntity updatedBookEntity = bookRepository.getById(TEST_EXISTING_BOOK_ID);
        assertThat(updatedBookEntity.getAuthorId()).isEqualTo(2);
        assertThat(updatedBookEntity.getGenreId()).isEqualTo(2);
        assertThat(updatedBookEntity.getBookName()).isEqualTo(TEST_NEW_BOOK_NAME);
    }
}
