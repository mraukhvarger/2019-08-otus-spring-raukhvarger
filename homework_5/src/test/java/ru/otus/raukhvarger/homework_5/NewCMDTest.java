package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.Shell;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@DisplayName("Команды создания")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewCMDTest extends RootTest {

	@BeforeAll
	public void run() {
		runCommand("new");
	}

	@Test
	@DisplayName("должны сохранять новых авторов")
	public void newAuthor() {
		String result = runCommand("newAuthor Test Author");
		Assertions.assertTrue(result.contains("Test Author"));
	}

	@Test
	@DisplayName("должны сохранять новые книги")
	public void newBook() {
		String result = runCommand("newBook TestBook");
		Assertions.assertTrue(result.contains("TestBook"));
	}

	@Test
	@DisplayName("должны сохранять новые жанры")
	public void newGenre() {
		String result = runCommand("newGenre TestGenre");
		Assertions.assertTrue(result.contains("TestGenre"));
	}

    @Test
    @DisplayName("должны добавлять авторов книгам")
    public void addAuthorToBook() {
        String result = runCommand("newAuthor Test Author");
        Integer authorId = getIdFromResult(result);

        result = runCommand("newBook TestBook");
        Integer bookId = getIdFromResult(result);

        result = runCommand(String.format("addAuthorToBook %s %s", authorId, bookId));
        Assertions.assertTrue(result.contains("Ок"));
    }

    @Test
    @DisplayName("должны добавлять книги авторам")
    public void addBookToAuthor() {
        String result = runCommand("newAuthor Test Author");
        Integer authorId = getIdFromResult(result);

        result = runCommand("newBook TestBook");
        Integer bookId = getIdFromResult(result);

        result = runCommand(String.format("addBookToAuthor %s %s", authorId, bookId));
        Assertions.assertTrue(result.contains("Ок"));
    }

    @Test
    @DisplayName("должны добавлять жанры книгам")
    public void addGenreToBook() {
        String result = runCommand("newBook TestBook");
        Integer bookId = getIdFromResult(result);

        result = runCommand("newGenre TestGenre");
        Integer genreId = getIdFromResult(result);

        result = runCommand(String.format("addGenreToBook %s %s", genreId, bookId));
        Assertions.assertTrue(result.contains("Ок"));
    }

}
