package ru.otus.raukhvarger.homework_8.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.raukhvarger.homework_8.utils.shell.comment.CommentShellUtilsProvider;

@ShellComponent
public class CommentShellCommander {
    private final CommentShellUtilsProvider provider;

    public CommentShellCommander(CommentShellUtilsProvider provider) {
        this.provider = provider;
    }

    @ShellMethod(key = {"cc", "ccom"}, value = "Create comment on book")
    public void createComment(String bookId) {
        provider.create(bookId);
    }

    @ShellMethod(key = {"cu", "cupd"}, value = "Update comment by id")
    public void updateComment(String id) {
        provider.update(id);
    }

    @ShellMethod(key = {"cd", "cdel"}, value = "Delete comment by id")
    public void deleteComment(String id) {
        provider.deleteById(id);
    }

    @ShellMethod(key = {"cb", "cl", "clist"}, value = "Browse all comments")
    public void listComments() {
        provider.browse();
    }

    @ShellMethod(key = {"cbb", "cbl", "cblist"}, value = "Browse comments on book")
    public void listCommentsOnBook(String bookId) {
        provider.browseByBookId(bookId);
    }
}
