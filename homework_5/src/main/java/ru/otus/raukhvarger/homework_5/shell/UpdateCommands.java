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
public class UpdateCommands {

    private final IAuthorAndBookRepository ab;
    private final IAuthorRepository a;
    private final IBookRepository b;
    private final IGenreRepository g;
    private final MainCommands mainCommands;
    
    private static final String GROUP = "(4) Обновление";

    @Autowired
    public UpdateCommands(IAuthorAndBookRepository ab, IAuthorRepository a, IBookRepository b, IGenreRepository g, MainCommands mainCommands) {
        this.ab = ab;
        this.a = a;
        this.b = b;
        this.g = g;
        this.mainCommands = mainCommands;
    }

    @ShellMethod(key = { "updAuthor" }, value = "Обновление данных об автора", group = GROUP)
    @ShellMethodAvailability("check")
    public String updAuthor(Integer authorId, String firstName, String lastName) {
        Optional<AuthorEntity> authorOpt = a.findOne(authorId);
        if (authorOpt.isPresent()) {
            AuthorEntity findedAuthor = authorOpt.get();
            findedAuthor.setFirstName(firstName);
            findedAuthor.setLastName(lastName);
            a.update(findedAuthor);
            return "Ок";
        } else
            return "Автор не найден";
    }

    @ShellMethod(key = { "updBook" }, value = "Обновление данных о книге", group = GROUP)
    @ShellMethodAvailability("check")
    public String updBook(Integer bookId, String caption) {
        Optional<BookEntity> bookOpt = b.findOne(bookId);
        if (bookOpt.isPresent()) {
            BookEntity findedBook = bookOpt.get();
            findedBook.setCaption(caption);
            b.update(findedBook);
            return "Ок";
        } else
            return "Книга не найдена";
    }

    @ShellMethod(key = { "updGenre" }, value = "Обновление данных о жанре", group = GROUP)
    @ShellMethodAvailability("check")
    public String updGenre(Integer genreId, String genre) {
        Optional<GenreEntity> genreOpt = g.findOne(genreId);
        if (genreOpt.isPresent()) {
            GenreEntity findedGenre = genreOpt.get();
            findedGenre.setGenre(genre);
            g.update(findedGenre);
            return "Ок";
        } else
            return "Книга не найдена";
    }

    public Availability check() {
        return mainCommands.checkUpdate();
    }

}
