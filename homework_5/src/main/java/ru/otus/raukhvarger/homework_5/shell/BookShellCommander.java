package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.entity.BookEntity;
import ru.otus.raukhvarger.homework_5.entity.GenreEntity;
import ru.otus.raukhvarger.homework_5.service.book.BookProvider;
import ru.otus.raukhvarger.homework_5.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_5.utils.messages.MessageProvider;
import ru.otus.raukhvarger.homework_5.utils.shell.ShellUtils;

import java.util.List;

@ShellComponent
public class BookShellCommander {
    private final MessageProvider messageProvider;
    private final IOProvider ioProvider;
    private final BookProvider bookProvider;
    private final ShellUtils shUtils;

    public BookShellCommander(MessageProvider messageProvider, IOProvider ioProvider,
                              BookProvider bookProvider, ShellUtils shUtils) {
        this.messageProvider = messageProvider;
        this.ioProvider = ioProvider;
        this.bookProvider = bookProvider;
        this.shUtils = shUtils;
    }

    @ShellMethod(key = {"bc", "bcr"}, value = "Create book")
    public void createBook() {
        AuthorEntity authorEntity = shUtils.getAuthor();
        GenreEntity genreEntity = shUtils.getGenre();
        BookEntity bookEntity = shUtils.getBook();
        bookEntity.setAuthorEntity(authorEntity);
        bookEntity.setGenreEntity(genreEntity);
        bookProvider.createBook(bookEntity);
        ioProvider.print(messageProvider.getFormattedMessage("HW.BookCreated", bookEntity.getBookName()));
    }

    @ShellMethod(key = {"bg", "bgn"}, value = "Get books by name")
    public void getBooksByName(String bookName) {
        List<BookEntity> bookEntities = bookProvider.getBooksByName(bookName);
        if (bookEntities.size() > 0) {
            bookEntities.forEach(b -> ioProvider.print(b.toString()));
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundByName", bookName));
        }
    }

    @ShellMethod(key = {"bgi", "bgid"}, value = "Get book by id")
    public void getBookById(Integer bookId) {
        BookEntity bookEntity = bookProvider.getBookById(bookId);
        if (bookEntity != null) {
            ioProvider.print(bookEntity.toString());
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
        }
    }

    @ShellMethod(key = {"bb", "bl", "blist"}, value = "Browse all books")
    public void listBooks() {
        List<BookEntity> bookEntities = bookProvider.listBooks();
        bookEntities.forEach(b -> ioProvider.print(b.toString()));
    }

    @ShellMethod(key = {"bd", "bdi"}, value = "Delete book by id")
    public void deleteBookById(Integer bookId) {
        BookEntity bookEntity = bookProvider.getBookById(bookId);
        if (bookEntity != null) {
            bookProvider.deleteBookById(bookId);
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookDeleted", bookId.toString()));
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
        }
    }

    @ShellMethod(key = {"bu", "bupd"}, value = "Update book by id")
    public void updateBookById(Integer bookId) {
        BookEntity bookEntity = bookProvider.getBookById(bookId);
        if (bookEntity != null) {
            GenreEntity genreEntity = shUtils.getGenreForUpdate();
            AuthorEntity authorEntity = shUtils.getAuthorForUpdate();
            String bookName = shUtils.getBookNameForUpdate();
            if (genreEntity != null || authorEntity != null || bookName != null) {
                bookProvider.updateBook(bookEntity.getBookId(), authorEntity, genreEntity, bookName);
                ioProvider.print(messageProvider.getFormattedMessage("HW.BookUpdated", bookId.toString()));
            } else {
                ioProvider.print(messageProvider.getFormattedMessage("HW.BookNothingToUpdate", bookId.toString()));
            }
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
        }
    }
}
