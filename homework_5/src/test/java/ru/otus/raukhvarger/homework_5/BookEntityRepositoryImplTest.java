package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_5.db.AuthorRepositoryImpl;
import ru.otus.raukhvarger.homework_5.db.BookRepositoryImpl;
import ru.otus.raukhvarger.homework_5.db.GenreRepositoryImpl;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entity.BookEntity;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;
import ru.otus.raukhvarger.homework_5.spring.Application;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test book db provider")
@SpringBootTest(classes = Application.class)
@Transactional
public class BookEntityRepositoryImplTest {
    public static final String TEST_NEW_BOOK_NAME = "Чума";
    public static final String TEST_NEW_AUTHOR_NAME = "Альбер Камю";
    public static final String TEST_NEW_GENRE_NAME = "Философский роман";
    public static final Integer TEST_NEW_BOOK_ID = 6;

    public static final Integer TEST_EXPECTED_BOOKS_SIZE = 5;

    public static final String TEST_EXISTING_BOOK_NAME = "451 градус по фаренгейту";
    public static final Integer TEST_EXISTING_BOOK_ID = 1;
    @Autowired
    private AuthorRepositoryImpl authorRepositoryImpl;
    @Autowired
    private GenreRepositoryImpl genreRepositoryImpl;
    @Autowired
    private BookRepositoryImpl bookRepositoryImpl;

    @Test
    @DisplayName("test create book")
    void testCreateBook() {
        AuthorEntity authorEntity = new AuthorEntity(TEST_NEW_AUTHOR_NAME);
        authorRepositoryImpl.insertAuthor(authorEntity);
        AuthorEntity createdAuthorEntity = authorRepositoryImpl.getAuthorByName(TEST_NEW_AUTHOR_NAME);

        GenreEntity genreEntity = new GenreEntity(TEST_NEW_GENRE_NAME);
        genreRepositoryImpl.insertGenre(genreEntity);
        GenreEntity createdGenreEntity = genreRepositoryImpl.getGenreByName(TEST_NEW_GENRE_NAME);

        BookEntity bookEntity = new BookEntity(TEST_NEW_BOOK_NAME);
        bookEntity.setGenreEntity(createdGenreEntity);
        bookEntity.setAuthorEntity(createdAuthorEntity);
        bookRepositoryImpl.insertBook(bookEntity);

        BookEntity createdBookEntity = bookRepositoryImpl.getBookById(TEST_NEW_BOOK_ID);
        assertNotNull(createdBookEntity);
        assertEquals(TEST_NEW_BOOK_NAME, createdBookEntity.getBookName());
        assertEquals(createdAuthorEntity, createdBookEntity.getAuthorEntity());
        assertEquals(createdGenreEntity, createdBookEntity.getGenreEntity());
    }

    @Test
    @DisplayName("test delete book by id")
    void testDeleteBookById() {
        bookRepositoryImpl.deleteBookById(TEST_EXISTING_BOOK_ID);
        BookEntity existingBookEntity = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertNull(existingBookEntity);
    }

    @Test
    @DisplayName("test getting existing book by id")
    void testGetExistingBookById() {
        BookEntity dataBookEntity = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertNotNull(dataBookEntity);
        assertEquals(dataBookEntity.getBookName(), TEST_EXISTING_BOOK_NAME);
    }

    @Test
    @DisplayName("test getting book by name")
    void testGetBookByName() {
        List<BookEntity> existingBookEntities = bookRepositoryImpl.getBooksByName(TEST_EXISTING_BOOK_NAME);
        assertNotNull(existingBookEntities.get(0));
        BookEntity existingBookEntity = existingBookEntities.get(0);
        assertNotNull(existingBookEntity.getBookId());
        assertEquals(existingBookEntity.getBookId(), TEST_EXISTING_BOOK_ID);
    }

    @Test
    @DisplayName("test get all books")
    void testGetAllBooks() {
        List<BookEntity> bookEntities = bookRepositoryImpl.getAllBooks();
        assertThat(bookEntities.size()).isGreaterThan(1);
        assertThat(bookEntities.size()).isEqualTo(TEST_EXPECTED_BOOKS_SIZE);
    }

    @Test
    @DisplayName("test update book author")
    void testUpdateBookAuthor() {
        bookRepositoryImpl.updateBookAuthor(TEST_EXISTING_BOOK_ID, 2);
        BookEntity updatedBookEntity = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertThat(updatedBookEntity.getAuthorEntity().getAuthorId()).isEqualTo(2);
    }

    @Test
    @DisplayName("test update book genre")
    void testUpdateBookGenre() {
        bookRepositoryImpl.updateBookGenre(TEST_EXISTING_BOOK_ID, 2);
        BookEntity updatedBookEntity = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertThat(updatedBookEntity.getGenreEntity().getGenreId()).isEqualTo(2);
    }

    @Test
    @DisplayName("test update book name")
    void testUpdateBookName() {
        bookRepositoryImpl.updateBookName(TEST_EXISTING_BOOK_ID, TEST_NEW_BOOK_NAME);
        BookEntity updatedBookEntity = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertThat(updatedBookEntity.getBookName()).isEqualTo(TEST_NEW_BOOK_NAME);
    }
}
