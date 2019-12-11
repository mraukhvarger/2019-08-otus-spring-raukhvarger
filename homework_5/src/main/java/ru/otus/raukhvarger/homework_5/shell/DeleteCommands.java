package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.raukhvarger.homework_5.dao.AuthorRepository;
import ru.otus.raukhvarger.homework_5.dao.BookRepository;
import ru.otus.raukhvarger.homework_5.dao.GenreRepository;
import ru.otus.raukhvarger.homework_5.entitiy.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entitiy.BookEntity;
import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import java.util.Optional;

@ShellComponent
public class DeleteCommands {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final MainCommands mainCommands;

    private static final String GROUP = "(5) Удаление";

    @Autowired
    public DeleteCommands(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, MainCommands mainCommands) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.mainCommands = mainCommands;
    }

    @ShellMethod(key = { "delAuthor" }, value = "Удаление автора", group = GROUP)
    @ShellMethodAvailability("check")
    public String delAuthor(Long authorId) {
        Optional<AuthorEntity> authorOpt = authorRepository.findOne(authorId);
        if (authorOpt.isPresent()) {
            authorRepository.delete(authorId);
            return "Ок";
        } else
            return "Автор не найден";
    }

    @ShellMethod(key = { "delBook" }, value = "Удаление книги", group = GROUP)
    @ShellMethodAvailability("check")
    public String delBook(Long bookId) {
        Optional<BookEntity> bookOpt = bookRepository.findOne(bookId);
        if (bookOpt.isPresent()) {
            bookRepository.delete(bookId);
            return "Ок";
        } else
            return "Книга не найдена";
    }

    @ShellMethod(key = { "delGenre" }, value = "Удаление жанра", group = GROUP)
    @ShellMethodAvailability("check")
    public String delGenre(Long genreId) {
        Optional<GenreEntity> genreOpt = genreRepository.findOne(genreId);
        if (genreOpt.isPresent()) {
            genreRepository.delete(genreId);
            return "Ок";
        } else
            return "Жанр не найден";
    }

    public Availability check() {
        return mainCommands.checkDelete();
    }

}
