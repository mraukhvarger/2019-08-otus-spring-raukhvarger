package ru.otus.raukhvarger.homework_8.utils.shell.comment;

public interface CommentShellUtilsProvider {
    void create(String bookId);
    void update(String id);
    void deleteById(String id);
    void browseByBookId(String bookId);
    void browse();
}
