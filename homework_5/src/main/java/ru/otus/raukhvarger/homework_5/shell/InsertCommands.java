package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
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

    private final IAuthorRepository authorRepository;
    private final IBookRepository bookRepository;
    private final IGenreRepository genreRepository;
    private final MainCommands mainCommands;
    
    private static final String GROUP = "(3) Создание";

    @Autowired
    public InsertCommands(IAuthorRepository authorRepository, IBookRepository bookRepository, IGenreRepository genreRepository, MainCommands mainCommands) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.mainCommands = mainCommands;
    }

    @ShellMethod(key = { "newAuthor" }, value = "Добавление нового автора", group = GROUP)
    @ShellMethodAvailability("check")
    public String newAuthor(String firstName, String lastName) {
        Long id = authorRepository.saveAndReturnId(AuthorEntity.builder().firstName(firstName).lastName(lastName).build());

        return String.format("Автор -- %s %s (%s)", firstName, lastName, id);
    }

    @ShellMethod(key = { "newBook" }, value = "Добавление новой книги", group = GROUP)
    @ShellMethodAvailability("check")
    public String newBook(String caption) {
        Long id = bookRepository.saveAndReturnId(BookEntity.builder().caption(caption).build());

        return String.format("Книга -- %s (%s)", caption, id);
    }

    @ShellMethod(key = { "newGenre" }, value = "Добавление нового жанра", group = GROUP)
    @ShellMethodAvailability("check")
    public String newGenre(String genre) {
        Long id = genreRepository.saveAndReturnId(GenreEntity.builder().genre(genre).build());

        return String.format("Жанр -- %s (%s)", genre, id);
    }

    @ShellMethod(key = { "addBookToAuthor" }, value = "Добавление книги автору", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> addBookToAuthor(Long bookId, Long authorId) {
        Optional<AuthorEntity> author = authorRepository.findOne(authorId);
        Optional<BookEntity> book = bookRepository.findOne(bookId);

        List<String> result = new ArrayList<>();
        if (!book.isPresent())
            result.add("Книга не найдена");
        if (!author.isPresent())
            result.add("Автор не найден");

        author.ifPresent(a -> book.ifPresent(b -> {
            a.getBooks().add(b);
            authorRepository.update(a);
            result.add("Ок");
        }));

        return result;
    }

    @ShellMethod(key = { "addAuthorToBook" }, value = "Добавление автора книге", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> addAuthorToBook(Long authorId, Long bookId) {
        Optional<AuthorEntity> author = authorRepository.findOne(authorId);
        Optional<BookEntity> book = bookRepository.findOne(bookId);

        List<String> result = new ArrayList<>();
        if (!author.isPresent())
            result.add("Автор не найден");
        if (!book.isPresent())
            result.add("Книга не найдена");

        author.ifPresent(a -> book.ifPresent(b -> {
            b.getAuthors().add(a);
            bookRepository.update(b);
            result.add("Ок");
        }));

        return result;
    }

    @ShellMethod(key = { "addGenreToBook" }, value = "Добавление жанра книге", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> addGanreToBook(Long genreId, Long bookId) {
        Optional<GenreEntity> genre = genreRepository.findOne(genreId);
        Optional<BookEntity> book = bookRepository.findOne(bookId);

        List<String> result = new ArrayList<>();

        if (!genre.isPresent())
            result.add("Жанр не найден");
        if (!book.isPresent())
            result.add("Книга не найдена");

        genre.ifPresent(g -> book.ifPresent(b -> {
            b.setGenre(g);
            bookRepository.update(b);
            result.add("Ок");
        }));

        return result;
    }

    public Availability check() {
        return mainCommands.checkInsert();
    }

}
