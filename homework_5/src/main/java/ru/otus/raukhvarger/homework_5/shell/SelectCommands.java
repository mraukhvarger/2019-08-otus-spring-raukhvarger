package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.raukhvarger.homework_5.dao.AuthorRepository;
import ru.otus.raukhvarger.homework_5.dao.BookRepository;
import ru.otus.raukhvarger.homework_5.dao.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class SelectCommands {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final MainCommands mainCommands;

    private static final String GROUP = "(2) Выборка";

    @Autowired
    public SelectCommands(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, MainCommands mainCommands) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.mainCommands = mainCommands;
    }

    @ShellMethod(key = { "byAuthor" }, value = "Поиск автора и его книг по фамилии", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> authorWithBooks(String lastName) {
        List<String> result = new ArrayList<>();
        authorRepository.findAllByLastNameLike(lastName).stream().forEach(author -> {
            result.add(String.format("Автор -- %s %s (%s)", author.getFirstName(), author.getLastName(), author.getId()));
            result.add("Книги -- " + author.getBooks().stream()
                    .map(m -> "[Название: " + m.getCaption()
                            + "; Жанр: " + (m.getGenre() != null ? m.getGenre().getGenre() : "")
                            + "]")
                    .collect(Collectors.joining(",\n\t")));
            result.add("");
        });

        return result;
    }

    @ShellMethod(key = { "byBook" }, value = "Поиск книги и ее авторов по названию", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> bookWithAuthors(String caption) {
        List<String> result = new ArrayList<>();

        bookRepository.findAllByCaptionLike(caption).stream().forEach(book -> {
            result.add(String.format("Книга -- %s (%s)", book.getCaption(), book.getId()));
            result.add("Авторы -- " + book.getAuthors().stream()
                    .map(m -> String.format("%s %s", m.getFirstName(), m.getLastName()))
                    .collect(Collectors.joining(",\n\t")));
            result.add("");
        });

        return result;
    }

    @ShellMethod(key = { "byGenre" }, value = "Поиск книг по жанру", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> booksByGenres(String genre) {
        List<String> result = new ArrayList<>();

        genreRepository.findAllByGenreLike(genre).stream().forEach(gn -> {
            result.add(String.format("Жанр -- %s (%s)", gn.getGenre(), gn.getId()));
            result.add("Кники -- " + gn.getBooks().stream()
                    .map(bk -> String.format("%s (%s)", bk.getCaption(), bk.getId()))
                    .collect(Collectors.joining(",\n\t")));
        });

        return result;
    }

    @ShellMethod(key = { "books" }, value = "Выборка всех книг", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> books() {
        List<String> result = new ArrayList<>();

        bookRepository.findAll().stream().
                forEach(m -> {
                    result.add(String.format("Книга -- %s (%s)", m.getCaption(), m.getId()));
                    result.add("");
                });

        return result;
    }

    @ShellMethod(key = { "authors" }, value = "Выборка всех авторов", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> authors() {
        List<String> result = new ArrayList<>();

        authorRepository.findAll().stream().
                forEach(m -> {
                    result.add(String.format("Автор -- %s %s (%s)", m.getFirstName(), m.getLastName(), m.getId()));
                    result.add("");
                });

        return result;
    }

    @ShellMethod(key = { "genres" }, value = "Выборка всех жанров", group = GROUP)
    @ShellMethodAvailability("check")
    public List<String> genres() {
        List<String> result = new ArrayList<>();

        genreRepository.findAll().stream().
                forEach(m -> {
                    result.add(String.format("Жанр -- %s (%s)", m.getGenre(), m.getId()));
                    result.add("");
                });

        return result;
    }

    public Availability check() {
        return mainCommands.checkSelect();
    }

}
