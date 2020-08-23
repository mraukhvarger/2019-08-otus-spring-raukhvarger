package ru.otus.raukhvarger.homework_6.utils.shell.book;

public interface BookShellUtilsProvider {
    void create();

    void updateById(Long id);

    void deleteById(Long id);

    void getByName(String name);

    void getById(Long id);

    void browse();

    void getByAuthorName(String authorName);
}
