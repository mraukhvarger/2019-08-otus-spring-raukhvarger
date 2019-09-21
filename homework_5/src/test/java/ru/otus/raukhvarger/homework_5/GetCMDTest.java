package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.*;

import java.io.IOException;

@DisplayName("Команды выборки")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetCMDTest extends RootTest {

    Integer author_1;
    Integer author_2;
    Integer author_3;

    Integer book_1;
    Integer book_2;
    Integer book_3;

    Integer genre_1;
    Integer genre_2;
    Integer genre_3;

	@BeforeAll
	public void run() {
		runCommand("new");

        author_1 = getIdFromResult(runCommand("newAuthor Test_1 Author_1"));
        author_2 = getIdFromResult(runCommand("newAuthor Test_2 Author_2"));
        author_3 = getIdFromResult(runCommand("newAuthor Test_3 Author_3"));

        book_1 = getIdFromResult(runCommand("newBook TestBook_1"));
        book_2 = getIdFromResult(runCommand("newBook TestBook_2"));
        book_3 = getIdFromResult(runCommand("newBook TestBook_3"));

        genre_1 = getIdFromResult(runCommand("newGenre TestGenre_1"));
        genre_2 = getIdFromResult(runCommand("newGenre TestGenre_2"));
        genre_3 = getIdFromResult(runCommand("newGenre TestGenre_3"));

        runCommand(String.format("addAuthorToBook %s %s", author_1, book_1));
        runCommand(String.format("addBookToAuthor %s %s", book_2, author_2));
        runCommand(String.format("addGenreToBook %s %s", genre_2, book_1));
        runCommand(String.format("addGenreToBook %s %s", genre_3, book_2));
        runCommand(String.format("addGenreToBook %s %s", genre_3, book_3));

        runCommand("get");
	}

	@Test
	@DisplayName("должны извлекать список всех авторов")
	public void authors() throws IOException {
        String result = runCommand("authors");
		Assertions.assertTrue(result.contains("Author_1")
                && result.contains("Author_2")
                && result.contains("Author_3"));
	}

	@Test
	@DisplayName("должны извлекать список всех книг")
	public void books() throws IOException {
        String result = runCommand("books");
		Assertions.assertTrue(result.contains("TestBook_1")
                && result.contains("TestBook_2")
                && result.contains("TestBook_3"));
	}

	@Test
	@DisplayName("должны извлекать список всех жанров")
	public void genres() throws IOException {
        String result = runCommand("genres");
		Assertions.assertTrue(result.contains("TestGenre_1")
                && result.contains("TestGenre_2")
                && result.contains("TestGenre_3"));
	}

    @Test
    @DisplayName("должны находить книги по авторам")
    public void byAuthor() throws IOException {
        String result = runCommand("byAuthor Author_1");
        Assertions.assertTrue(result.contains("TestBook_1"));
    }

    @Test
    @DisplayName("должны находить авторов по книгам")
    public void byBook() throws IOException {
        String result = runCommand("byBook TestBook_2");
        Assertions.assertTrue(result.contains("Author_2"));
    }

    @Test
    @DisplayName("должны находить книги по жанрам")
    public void byGenre() throws IOException {
        String result = runCommand("byGenre TestGenre_3");
        Assertions.assertTrue(result.contains("TestBook_2")
                && result.contains("TestBook_3"));
    }

}
