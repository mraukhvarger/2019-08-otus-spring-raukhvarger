package ru.otus.raukhvarger.homework_8.utils.shell;

public interface ShellUtilsProvider {
    void create();
    void update();
    void deleteById(String id);
    void browse();
}
