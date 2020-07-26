package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_5.db.BookRepository;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entity.BookEntity;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;
import ru.otus.raukhvarger.homework_5.service.book.BookImpl;
import ru.otus.raukhvarger.homework_5.spring.Application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@DisplayName("Test book impl provider")
@SpringBootTest(classes = Application.class)
public class BookEntityImplTest {

    public static final String TEST_EXISTING_BOOK_NAME = "test book";
    public static final Integer TEST_EXISTING_BOOK_ID = 3;

    @Test
    @DisplayName("test getting book by id")
    void testGetBookById() {
        BookRepository bookRepository = mock(BookRepository.class);
        Mockito.when(bookRepository.getBookById(TEST_EXISTING_BOOK_ID)).thenReturn(
                new BookEntity(TEST_EXISTING_BOOK_ID, TEST_EXISTING_BOOK_NAME, new AuthorEntity(), new GenreEntity())
        );
        BookImpl bookImpl = new BookImpl(bookRepository);
        BookEntity dataBookEntity = bookImpl.getBookById(TEST_EXISTING_BOOK_ID);
        assertNotNull(dataBookEntity);
        assertEquals(dataBookEntity.getBookName(), TEST_EXISTING_BOOK_NAME);
    }
}
