package ru.otus.raukhvarger.homework_8.db.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.raukhvarger.homework_8.db.entity.Author;
import ru.otus.raukhvarger.homework_8.db.entity.Genre;
import ru.otus.raukhvarger.homework_8.db.repository.AuthorRepository;
import ru.otus.raukhvarger.homework_8.db.repository.BookRepository;
import ru.otus.raukhvarger.homework_8.db.repository.GenreRepository;
import ru.otus.raukhvarger.homework_8.db.entity.Book;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test book repository")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.raukhvarger.homework_8"})
public class BookRepositoryTest {
    public static final String TEST_NEW_BOOK_NAME = "Plague";
    public static final String TEST_NEW_AUTHOR_NAME = "Albert Camus";
    public static final String TEST_NEW_GENRE_NAME = "Philosophical novel";
    public static final String TEST_NEW_BOOK_ID = "89832a66-4dc1-4979-b3ff-e04623719bea";

    public static final Integer TEST_EXPECTED_BOOKS_SIZE = 5;

    public static final String TEST_EXISTING_BOOK_NAME = "Fahrenheit 451";
    public static final String TEST_EXISTING_BOOK_ID = "95006896-12c4-4531-b11c-8ff153e44d70";
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("test get all books")
    void testGetAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isGreaterThan(1);
        assertThat(books.size()).isEqualTo(TEST_EXPECTED_BOOKS_SIZE);
    }

    @Test
    @DisplayName("test create book")
    void testCreateBook() {
        Author author = Author.builder()
                .authorId(UUID.randomUUID().toString())
                .authorName(TEST_NEW_AUTHOR_NAME)
                .build();
        authorRepository.save(author);
        Author createdAuthor = authorRepository.findByAuthorNameIgnoreCase(TEST_NEW_AUTHOR_NAME).orElse(Author.builder().build());

        Genre genre = Genre.builder()
                .genreId(UUID.randomUUID().toString())
                .genreName(TEST_NEW_GENRE_NAME)
                .build();
        genreRepository.save(genre);
        Genre createdGenre= genreRepository.findByGenreName(TEST_NEW_GENRE_NAME).orElse(Genre.builder().build());

        Book book = Book.builder()
                .bookId(TEST_NEW_BOOK_ID)
                .bookName(TEST_NEW_BOOK_NAME)
                .genre(createdGenre)
                .author(createdAuthor)
                .build();
        bookRepository.save(book);

        Book createdBook = bookRepository.findById(TEST_NEW_BOOK_ID).orElse(Book.builder().build());
        assertNotNull(createdBook);
        assertEquals(TEST_NEW_BOOK_NAME, createdBook.getBookName());
        assertEquals(createdAuthor, createdBook.getAuthor());
        assertEquals(createdGenre, createdBook.getGenre());
    }

    @Test
    @DisplayName("test getting book by bookId")
    void testGetBookById() {
        Book dataBook = bookRepository.findById(TEST_EXISTING_BOOK_ID).orElse(Book.builder().build());
        assertNotNull(dataBook);
        assertEquals(dataBook.getBookName(), TEST_EXISTING_BOOK_NAME);
    }

    @Test
    @DisplayName("test getting book by name")
    void testGetBookByName() {
        List<Book> existingBooks = bookRepository.findByBookName(TEST_EXISTING_BOOK_NAME);
        assertNotNull(existingBooks.get(0));
        Book existingBook = existingBooks.get(0);
        assertNotNull(existingBook.getBookId());
        assertEquals(existingBook.getBookId(), TEST_EXISTING_BOOK_ID);
    }

}
