package ru.otus.raukhvarger.homework_6.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.raukhvarger.homework_6.utils.shell.book.BookShellUtilsProvider;

@ShellComponent
public class BookShellCommander {
    private final BookShellUtilsProvider provider;

    public BookShellCommander(BookShellUtilsProvider provider) {
        this.provider = provider;
    }

    @ShellMethod(key = {"bc", "bcr"}, value = "Create book")
    public void createBook() {
        provider.create();
    }

    @ShellMethod(key = {"bu", "bupd"}, value = "Update book by id")
    public void updateBook(Integer bookId) {
        provider.updateById(bookId);
    }

    @ShellMethod(key = {"bd", "bdel"}, value = "Delete book by id")
    public void deleteBook(Integer bookId) {
        provider.deleteById(bookId);
    }

    @ShellMethod(key = {"bb", "bl", "blist"}, value = "Browse all books")
    public void browseBooks() {
        provider.browse();
    }

    @ShellMethod(key = {"bgi"}, value = "Get book by id")
    public void getBookById(Integer bookId) {
        provider.getById(bookId);
    }

    @ShellMethod(key = {"bg", "bgn"}, value = "Get book by name")
    public void getBookByName(String bookName) {
        provider.getByName(bookName);
    }

    @ShellMethod(key = {"bga", "bgba"}, value = "Get books by author")
    public void getBooksByAuthor(String authorName) {
        provider.getByAuthorName(authorName);
    }
}
