package ru.otus.raukhvarger.homework_5.utils.shell;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.raukhvarger.homework_5.entity.Author;
import ru.otus.raukhvarger.homework_5.entity.Book;
import ru.otus.raukhvarger.homework_5.entity.Genre;
import ru.otus.raukhvarger.homework_5.service.author.AuthorProvider;
import ru.otus.raukhvarger.homework_5.service.book.BookProvider;
import ru.otus.raukhvarger.homework_5.service.genre.GenreProvider;
import ru.otus.raukhvarger.homework_5.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_5.utils.messages.MessageProvider;

@Service
public class ShellUtils {

    private final IOProvider ioProvider;
    private final MessageProvider messageProvider;
    private final GenreProvider genreProvider;
    private final AuthorProvider authorProvider;
    private final BookProvider bookProvider;

    public ShellUtils(IOProvider ioProvider, MessageProvider messageProvider,
                      GenreProvider genreProvider, AuthorProvider authorProvider, BookProvider bookProvider) {
        this.ioProvider = ioProvider;
        this.messageProvider = messageProvider;
        this.genreProvider = genreProvider;
        this.authorProvider = authorProvider;
        this.bookProvider = bookProvider;
    }

    public Book getBook() {
        ioProvider.print(messageProvider.getMessage("HW.EnterBookName"));
        boolean entered = false;
        while (!entered) {
            String bookName = ioProvider.read();
            if (StringUtils.isEmpty(bookName)) {
                ioProvider.print(messageProvider.getMessage("HW.BookNameNotEmpty"));
            } else {
                Book book = new Book(bookName);
                return book;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    public Genre getGenre() {
        ioProvider.print(messageProvider.getMessage("HW.EnterGenreName"));
        boolean entered = false;
        while (!entered) {
            String genreName = ioProvider.read();
            if (StringUtils.isEmpty(genreName)) {
                ioProvider.print(messageProvider.getMessage("HW.GenreNameNotEmpty"));
            } else {
                Genre genre = genreProvider.getOrCreateGenreByName(genreName);
                return genre;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    public Author getAuthor() {
        ioProvider.print(messageProvider.getMessage("HW.EnterAuthorName"));
        boolean entered = false;
        while (!entered) {
            String authorName = ioProvider.read();
            if (StringUtils.isEmpty(authorName)) {
                ioProvider.print(messageProvider.getMessage("HW.AuthorNameNotEmpty"));
            } else {
                Author author = authorProvider.getOrCreateAuthorByName(authorName);
                return author;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    public String getBookNameForUpdate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterBookName"));
        boolean entered = false;
        while (!entered) {
            String bookName = ioProvider.read();
            if (!StringUtils.isEmpty(bookName)) {
                return bookName;
            } else {
                entered = true;
            }
        }
        return null;
    }

    public Genre getGenreForUpdate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterGenreName"));
        boolean entered = false;
        while (!entered) {
            String genreName = ioProvider.read();
            if (!StringUtils.isEmpty(genreName)) {
                Genre genre = genreProvider.getOrCreateGenreByName(genreName);
                return genre;
            } else {
                entered = true;
            }
        }
        return null;
    }

    public Author getAuthorForUpdate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterAuthorName"));
        boolean entered = false;
        while (!entered) {
            String authorName = ioProvider.read();
            if (!StringUtils.isEmpty(authorName)) {
                Author author = authorProvider.getOrCreateAuthorByName(authorName);
                return author;
            } else {
                entered = true;
            }
        }
        return null;
    }
}
