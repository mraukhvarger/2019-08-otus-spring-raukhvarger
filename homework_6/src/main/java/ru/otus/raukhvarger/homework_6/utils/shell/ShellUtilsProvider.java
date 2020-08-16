package ru.otus.raukhvarger.homework_6.utils.shell;

public interface ShellUtilsProvider {
    void create();

    void update();

    void deleteById(Long id);

    void browse();
}
