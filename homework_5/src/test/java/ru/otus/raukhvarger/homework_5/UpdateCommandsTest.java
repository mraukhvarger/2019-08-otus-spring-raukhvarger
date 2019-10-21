package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.*;

import java.io.IOException;

@DisplayName("Команды обновления")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UpdateCommandsTest extends RootTest {

    Integer author_1;
    Integer book_1;
    Integer genre_1;

	@BeforeAll
	public void run() {
		runCommand("new");

        author_1 = getIdFromResult(runCommand("newAuthor Test Author"));
        book_1 = getIdFromResult(runCommand("newBook TestBook"));
        genre_1 = getIdFromResult(runCommand("newGenre TestGenre"));
	}

	@BeforeEach
    void runUpd() {
	    runCommand("upd");
    }

	@Test
	@DisplayName("должны изменять информацию об авторах")
	public void updAuthor() {
        String result = runCommand(String.format("updAuthor %s TestNew AuthorNew", author_1));
        Assertions.assertTrue(result.contains("Ок"));

        runCommand("get");
        result = runCommand("authors");
		Assertions.assertTrue(result.contains("TestNew"));
	}

	@Test
	@DisplayName("должны изменять информацию о книгах")
	public void updBook() {
        String result = runCommand(String.format("updBook %s TestNewBook", book_1));
        Assertions.assertTrue(result.contains("Ок"));

        runCommand("get");
        result = runCommand("books");
		Assertions.assertTrue(result.contains("TestNewBook"));
	}

	@Test
	@DisplayName("должны изменять информацию о жанрах")
	public void updGenre() {
        String result = runCommand(String.format("updGenre %s TestNewGenre", genre_1));
        Assertions.assertTrue(result.contains("Ок"));

        runCommand("get");
        result = runCommand("genres");
		Assertions.assertTrue(result.contains("TestNewGenre"));
	}

}
