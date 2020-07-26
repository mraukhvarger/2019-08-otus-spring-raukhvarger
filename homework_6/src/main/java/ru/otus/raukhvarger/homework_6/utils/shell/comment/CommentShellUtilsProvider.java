package ru.otus.raukhvarger.homework_6.utils.shell.comment;

public interface CommentShellUtilsProvider {
    void create(Integer bookId);

    void update(Integer id);

    void deleteById(Integer id);

    void browseByBookId(Integer bookId);

    void browse();
}
