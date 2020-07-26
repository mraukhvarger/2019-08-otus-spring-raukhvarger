package ru.otus.raukhvarger.homework_7.utils.shell;

public interface ShellUtilsProvider {
    void create();

    void update();

    void deleteById(Integer id);

    void browse();
}
