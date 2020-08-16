package ru.otus.raukhvarger.homework_5.service.book;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.raukhvarger.homework_5.db.BookRepository;
import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.entity.Genre;
import ru.otus.raukhvarger.homework_5.service.book.BookProviderImpl;
import ru.otus.raukhvarger.homework_5.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@DisplayName("Test book impl provider")
@SpringBootTest(classes = Application.class)
public class BookProviderImplTest {

    public static final String TEST_EXISTING_BOOK_NAME = "test book";
    public static final Integer TEST_EXISTING_BOOK_ID = 3;

    @MockBean BookRepository bookRepository;

    @Test
    @DisplayName("test getting book by id")
    void testGetBookById() {
        Mockito.when(bookRepository.getBookById(TEST_EXISTING_BOOK_ID)).thenReturn(
                new Book(TEST_EXISTING_BOOK_ID, TEST_EXISTING_BOOK_NAME, new Author(), new Genre())
        );
        BookProviderImpl bookProviderImpl = new BookProviderImpl(bookRepository);
        Book dataBook = bookProviderImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertNotNull(dataBook);
        assertEquals(dataBook.getBookName(), TEST_EXISTING_BOOK_NAME);
    }
}
