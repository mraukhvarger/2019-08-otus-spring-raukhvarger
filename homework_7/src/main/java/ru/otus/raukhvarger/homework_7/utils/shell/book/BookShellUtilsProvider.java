package ru.otus.raukhvarger.homework_7.utils.shell.book;

public interface BookShellUtilsProvider {
    void create();

    void updateById(Integer id);

    void deleteById(Integer id);

    void getByName(String name);

    void getById(Integer id);

    void browse();

    void getByAuthorName(String authorName);
}
