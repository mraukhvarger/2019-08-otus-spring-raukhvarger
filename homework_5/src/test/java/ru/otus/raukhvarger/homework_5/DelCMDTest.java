package ru.otus.raukhvarger.homework_5;

import org.junit.jupiter.api.*;

@DisplayName("Команды удаления")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DelCMDTest extends RootTest {

    Integer author_1;
    Integer book_1;
    Integer genre_1;

	@BeforeAll
	public void run() {
		runCommand("new");

        author_1 = getIdFromResult(runCommand("newAuthor TestDel AuthorDel"));
        book_1 = getIdFromResult(runCommand("newBook TestBookDel"));
        genre_1 = getIdFromResult(runCommand("newGenre TestGenreDel"));
	}

	@BeforeEach
    void runUpd() {
	    runCommand("del");
    }

	@Test
	@DisplayName("должны удалять авторов")
	public void delAuthor() {
        String result = runCommand(String.format("delAuthor %s", author_1));
        Assertions.assertTrue(result.contains("Ок"));

        runCommand("get");
        result = runCommand("authors");
		Assertions.assertFalse(result.contains("TestDel AuthorDel"));
	}

	@Test
	@DisplayName("должны удалять книги")
	public void delBook() {
        String result = runCommand(String.format("delBook %s", book_1));
        Assertions.assertTrue(result.contains("Ок"));

        runCommand("get");
        result = runCommand("books");
		Assertions.assertFalse(result.contains("TestBookDel"));
	}

	@Test
	@DisplayName("должны удалять жанры")
	public void delGenre() {
        String result = runCommand(String.format("delGenre %s", genre_1));
        Assertions.assertTrue(result.contains("Ок"));

        runCommand("get");
        result = runCommand("genres");
		Assertions.assertFalse(result.contains("TestGenreDel"));
	}

}
