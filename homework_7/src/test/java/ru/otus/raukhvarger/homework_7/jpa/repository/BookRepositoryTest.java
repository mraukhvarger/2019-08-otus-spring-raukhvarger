package ru.otus.raukhvarger.homework_7.jpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_7.jpa.entity.Author;
import ru.otus.raukhvarger.homework_7.jpa.entity.Book;
import ru.otus.raukhvarger.homework_7.jpa.entity.Genre;
import ru.otus.raukhvarger.homework_7.spring.Application;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test book repository")
@SpringBootTest(classes = Application.class)
@Transactional
public class BookRepositoryTest {
    public static final String TEST_NEW_BOOK_NAME = "Чума";
    public static final String TEST_NEW_AUTHOR_NAME = "Альбер Камю";
    public static final String TEST_NEW_GENRE_NAME = "Философский роман";
    public static final Integer TEST_NEW_BOOK_ID = 6;

    public static final Integer TEST_EXPECTED_BOOKS_SIZE = 5;

    public static final String TEST_EXISTING_BOOK_NAME = "451 градус по фаренгейту";
    public static final Integer TEST_EXISTING_BOOK_ID = 1;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("test create book")
    void testCreateBook() {
        Author author = Author.builder()
                .authorName(TEST_NEW_AUTHOR_NAME)
                .build();
        authorRepository.save(author);
        Author createdAuthor = authorRepository.findByAuthorName(TEST_NEW_AUTHOR_NAME).orElse(Author.builder().build());

        Genre genre = Genre.builder()
                .genreName(TEST_NEW_GENRE_NAME)
                .build();
        genreRepository.save(genre);
        Genre createdGenre = genreRepository.findByGenreName(TEST_NEW_GENRE_NAME).orElse(Genre.builder().build());

        Book book = Book.builder()
                .bookName(TEST_NEW_BOOK_NAME)
                .genreId(createdGenre.getGenreId())
                .authorId(createdAuthor.getAuthorId())
                .build();
        bookRepository.save(book);

        Book createdBook = bookRepository.findById(TEST_NEW_BOOK_ID).orElse(Book.builder().build());
        assertNotNull(createdBook);
        assertEquals(TEST_NEW_BOOK_NAME, createdBook.getBookName());
        assertEquals(createdAuthor.getAuthorId(), createdBook.getAuthorId());
        assertEquals(createdGenre.getGenreId(), createdBook.getGenreId());
    }

    @Test
    @DisplayName("test delete book by id")
    void testDeleteBookById() {
        bookRepository.deleteById(TEST_EXISTING_BOOK_ID);
        Book existingBook = bookRepository.findById(TEST_EXISTING_BOOK_ID).orElse(null);
        assertNull(existingBook);
    }

    @Test
    @DisplayName("test getting book by id")
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

    @Test
    @DisplayName("test get all books")
    void testGetAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertThat(books.size()).isGreaterThan(1);
        assertThat(books.size()).isEqualTo(TEST_EXPECTED_BOOKS_SIZE);
    }

    @Test
    @DisplayName("test update book")
    void testUpdateBook() {
        Book book = bookRepository.findById(TEST_EXISTING_BOOK_ID).orElse(Book.builder().build());
        book.setAuthorId(2);
        book.setGenreId(2);
        book.setBookName(TEST_NEW_BOOK_NAME);
        bookRepository.save(book);
        Book updatedBook = bookRepository.findById(TEST_EXISTING_BOOK_ID).orElse(Book.builder().build());
        assertThat(updatedBook.getAuthorId()).isEqualTo(2);
        assertThat(updatedBook.getGenreId()).isEqualTo(2);
        assertThat(updatedBook.getBookName()).isEqualTo(TEST_NEW_BOOK_NAME);
    }
}
