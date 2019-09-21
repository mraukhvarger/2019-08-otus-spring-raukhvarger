package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.raukhvarger.homework_5.dao.IAuthorAndBookRepository;
import ru.otus.raukhvarger.homework_5.dao.IAuthorRepository;
import ru.otus.raukhvarger.homework_5.dao.IBookRepository;
import ru.otus.raukhvarger.homework_5.dao.IGenreRepository;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class InsertCommands {

    private final IAuthorAndBookRepository ab;
    private final IAuthorRepository a;
    private final IBookRepository b;
    private final IGenreRepository g;
    private final MainCommands mainCommands;
    
    private static final String GROUP = "(3) Создание";

    @Autowired
    public InsertCommands(IAuthorAndBookRepository ab, IAuthorRepository a, IBookRepository b, IGenreRepository g, MainCommands mainCommands) {
        this.ab = ab;
        this.a = a;
        this.b = b;
        this.g = g;
        this.mainCommands = mainCommands;
    }

    @ShellMethod(key = { "newAuthor" }, value = "Добавление нового автора", group = GROUP)
    @ShellMethodAvailability("check")
    public String newAuthor(String firstName, String lastName) {
        Integer id = a.saveAndReturnId(new AuthorEntity(firstName, lastName));

        return String.format("Автор -- %s %s (%s)", firstName, lastName, id);
    }

    @ShellMethod(key = { "newBook" }, value = "Добавление новой книги", group = GROUP)
    @ShellMethodAvailability("check")
    public String newBook(String caption) {
        Integer id = b.saveAndReturnId(new BookEntity(caption));

        return String.format("Книга -- %s (%s)", caption, id);
    }

    @ShellMethod(key = { "newGenre" }, value = "Добавление нового жанра", group = GROUP)
    @ShellMethodAvailability("check")
    public String newGenre(String genre) {
        Integer id = g.saveAndReturnId(new GenreEntity(genre));

        return String.format("Жанр -- %s (%s)", genre, id);
    }

    @ShellMethod(key = { "addBookToAuthor" }, value = "Добавление книги автору", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> addBookToAuthor(Integer bookId, Integer authorId) {
        Optional<AuthorEntity> author = a.findOne(authorId);
        Optional<BookEntity> book = b.findOne(bookId);

        List<String> result = new ArrayList<>();
        if (!book.isPresent())
            result.add("Книга не найдена");
        if (!author.isPresent())
            result.add("Автор не найден");

        if (result.isEmpty()) {
            ab.save(authorId, bookId);
            result.add("Ок");
        }

        return result;
    }

    @ShellMethod(key = { "addAuthorToBook" }, value = "Добавление автора книге", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> addAuthorToBook(Integer authorId, Integer bookId) {
        Optional<AuthorEntity> author = a.findOne(authorId);
        Optional<BookEntity> book = b.findOne(bookId);

        List<String> result = new ArrayList<>();
        if (!author.isPresent())
            result.add("Автор не найден");
        if (!book.isPresent())
            result.add("Книга не найдена");

        if (result.isEmpty()) {
            ab.save(authorId, bookId);
            result.add("Ок");
        }

        return result;
    }

    @ShellMethod(key = { "addGenreToBook" }, value = "Добавление жанра книге", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> addGanreToBook(Integer genreId, Integer bookId) {
        Optional<GenreEntity> genre = g.findOne(genreId);
        Optional<BookEntity> book = b.findOne(bookId);

        List<String> result = new ArrayList<>();

        if (!genre.isPresent())
            result.add("Жанр не найден");
        if (!book.isPresent())
            result.add("Книга не найдена");

        if (result.isEmpty()) {
            BookEntity finedBook = book.get();
            finedBook.setIdGenre(genreId);
            b.update(finedBook);
            result.add("Ок");
        }
        return result;
    }

    public Availability check() {
        return mainCommands.checkInsert();
    }

}
