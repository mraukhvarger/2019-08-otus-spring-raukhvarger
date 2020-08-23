package ru.otus.raukhvarger.homework_6.utils.shell.comment;

public interface CommentShellUtilsProvider {
    void create(Long bookId);

    void update(Long id);

    void deleteById(Long id);

    void browseByBookId(Long bookId);

    void browse();
}
