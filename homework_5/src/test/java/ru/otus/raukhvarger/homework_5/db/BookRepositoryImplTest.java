package ru.otus.raukhvarger.homework_5.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.entity.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test book db provider")
@JdbcTest
@Import({Configuration.class})
@Transactional
public class BookRepositoryImplTest {
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
        Author author = new Author(TEST_NEW_AUTHOR_NAME);
        authorRepositoryImpl.insertAuthor(author);
        Author createdAuthor = authorRepositoryImpl.getAuthorByName(TEST_NEW_AUTHOR_NAME);

        Genre genre = new Genre(TEST_NEW_GENRE_NAME);
        genreRepositoryImpl.insertGenre(genre);
        Genre createdGenre = genreRepositoryImpl.getGenreByName(TEST_NEW_GENRE_NAME);

        Book book = new Book(TEST_NEW_BOOK_NAME);
        book.setGenre(createdGenre);
        book.setAuthor(createdAuthor);
        bookRepositoryImpl.insertBook(book);

        Book createdBook = bookRepositoryImpl.getBookById(TEST_NEW_BOOK_ID);
        assertNotNull(createdBook);
        assertEquals(TEST_NEW_BOOK_NAME, createdBook.getBookName());
        assertEquals(createdAuthor, createdBook.getAuthor());
        assertEquals(createdGenre, createdBook.getGenre());
    }

    @Test
    @DisplayName("test delete book by id")
    void testDeleteBookById() {
        bookRepositoryImpl.deleteBookById(TEST_EXISTING_BOOK_ID);
        Book existingBook = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertNull(existingBook);
    }

    @Test
    @DisplayName("test getting existing book by id")
    void testGetExistingBookById() {
        Book dataBook = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertNotNull(dataBook);
        assertEquals(dataBook.getBookName(), TEST_EXISTING_BOOK_NAME);
    }

    @Test
    @DisplayName("test getting book by name")
    void testGetBookByName() {
        List<Book> existingBookEntities = bookRepositoryImpl.getBooksByName(TEST_EXISTING_BOOK_NAME);
        assertNotNull(existingBookEntities.get(0));
        Book existingBook = existingBookEntities.get(0);
        assertNotNull(existingBook.getBookId());
        assertEquals(existingBook.getBookId(), TEST_EXISTING_BOOK_ID);
    }

    @Test
    @DisplayName("test get all books")
    void testGetAllBooks() {
        List<Book> bookEntities = bookRepositoryImpl.getAllBooks();
        assertThat(bookEntities.size()).isGreaterThan(1);
        assertThat(bookEntities.size()).isEqualTo(TEST_EXPECTED_BOOKS_SIZE);
    }

    @Test
    @DisplayName("test update book author")
    void testUpdateBookAuthor() {
        bookRepositoryImpl.updateBookAuthor(TEST_EXISTING_BOOK_ID, 2);
        Book updatedBook = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertThat(updatedBook.getAuthor().getAuthorId()).isEqualTo(2);
    }

    @Test
    @DisplayName("test update book genre")
    void testUpdateBookGenre() {
        bookRepositoryImpl.updateBookGenre(TEST_EXISTING_BOOK_ID, 2);
        Book updatedBook = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertThat(updatedBook.getGenre().getGenreId()).isEqualTo(2);
    }

    @Test
    @DisplayName("test update book name")
    void testUpdateBookName() {
        bookRepositoryImpl.updateBookName(TEST_EXISTING_BOOK_ID, TEST_NEW_BOOK_NAME);
        Book updatedBook = bookRepositoryImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertThat(updatedBook.getBookName()).isEqualTo(TEST_NEW_BOOK_NAME);
    }
}
