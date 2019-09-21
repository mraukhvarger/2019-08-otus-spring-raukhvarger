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

import java.util.Optional;

@ShellComponent
public class DeleteCommands {

    private final IAuthorAndBookRepository ab;
    private final IAuthorRepository a;
    private final IBookRepository b;
    private final IGenreRepository g;
    private final MainCommands mainCommands;

    private static final String GROUP = "(5) Удаление";

    @Autowired
    public DeleteCommands(IAuthorAndBookRepository ab, IAuthorRepository a, IBookRepository b, IGenreRepository g, MainCommands mainCommands) {
        this.ab = ab;
        this.a = a;
        this.b = b;
        this.g = g;
        this.mainCommands = mainCommands;
    }

    @ShellMethod(key = { "delAuthor" }, value = "Удаление автора", group = GROUP)
    @ShellMethodAvailability("check")
    public String delAuthor(Integer authoriId) {
        Optional<AuthorEntity> authorOpt = a.findOne(authoriId);
        if (authorOpt.isPresent()) {
            ab.findAllBooksByAuthorId(authoriId).stream().forEach(b -> {
                ab.delete(authoriId, b.getId());
            });
            a.delete(authoriId);
            return "Ок";
        } else
            return "Автор не найден";
    }

    @ShellMethod(key = { "delBook" }, value = "Удаление книги", group = GROUP)
    @ShellMethodAvailability("check")
    public String delBook(Integer bookId) {
        Optional<BookEntity> bookOpt = b.findOne(bookId);
        if (bookOpt.isPresent()) {
            ab.findAllAuthorsByBookId(bookId).stream().forEach(a -> {
                ab.delete(a.getId(), bookId);
            });
            b.delete(bookId);
            return "Ок";
        } else
            return "Книга не найдена";
    }

    @ShellMethod(key = { "delGenre" }, value = "Удаление жанра", group = GROUP)
    @ShellMethodAvailability("check")
    public String delGenre(Integer genreId) {
        Optional<GenreEntity> genreOpt = g.findOne(genreId);
        if (genreOpt.isPresent()) {
            b.findAllByGenreEq(genreId).stream().forEach(bk -> {
                bk.setIdGenre(null);
                b.update(bk);
            });
            g.delete(genreId);
            return "Ок";
        } else
            return "Жанр не найден";
    }

    public Availability check() {
        return mainCommands.checkDelete();
    }

}
