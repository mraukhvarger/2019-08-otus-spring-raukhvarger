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
public class UpdateCommands {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final MainCommands mainCommands;
    
    private static final String GROUP = "(4) Обновление";

    @Autowired
    public UpdateCommands(AuthorRepository authorRepository, BookRepository bookRepository, GenreRepository genreRepository, MainCommands mainCommands) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.mainCommands = mainCommands;
    }

    @ShellMethod(key = { "updAuthor" }, value = "Обновление данных об автора", group = GROUP)
    @ShellMethodAvailability("check")
    public String updAuthor(Long authorId, String firstName, String lastName) {
        Optional<AuthorEntity> authorOpt = authorRepository.findOne(authorId);
        if (authorOpt.isPresent()) {
            AuthorEntity findedAuthor = authorOpt.get();
            findedAuthor.setFirstName(firstName);
            findedAuthor.setLastName(lastName);
            authorRepository.update(findedAuthor);
            return "Ок";
        } else
            return "Автор не найден";
    }

    @ShellMethod(key = { "updBook" }, value = "Обновление данных о книге", group = GROUP)
    @ShellMethodAvailability("check")
    public String updBook(Long bookId, String caption) {
        Optional<BookEntity> bookOpt = bookRepository.findOne(bookId);
        if (bookOpt.isPresent()) {
            BookEntity findedBook = bookOpt.get();
            findedBook.setCaption(caption);
            bookRepository.update(findedBook);
            return "Ок";
        } else
            return "Книга не найдена";
    }

    @ShellMethod(key = { "updGenre" }, value = "Обновление данных о жанре", group = GROUP)
    @ShellMethodAvailability("check")
    public String updGenre(Long genreId, String genre) {
        Optional<GenreEntity> genreOpt = genreRepository.findOne(genreId);
        if (genreOpt.isPresent()) {
            GenreEntity findedGenre = genreOpt.get();
            findedGenre.setGenre(genre);
            genreRepository.update(findedGenre);
            return "Ок";
        } else
            return "Книга не найдена";
    }

    public Availability check() {
        return mainCommands.checkUpdate();
    }

}
