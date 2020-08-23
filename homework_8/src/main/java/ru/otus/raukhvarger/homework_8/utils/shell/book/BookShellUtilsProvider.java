package ru.otus.raukhvarger.homework_8.utils.shell.book;

public interface BookShellUtilsProvider {
    void create();
    void updateById(String id);
    void deleteById(String id);
    void getByName(String name);
    void getById(String id);
    void browse();
    void getByAuthorName(String authorName);
}
