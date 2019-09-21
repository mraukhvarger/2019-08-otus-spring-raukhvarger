package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MainCommands {

    private StatusType state = StatusType.NONE;

    private static final String GROUP = "(1) Главное меню";

    @ShellMethod(key = { "a)", "get" }, value = "Выборка", group = GROUP)
    public String select() {
        state = StatusType.SELECT;
        return "Режим Выборки";
    }

    @ShellMethod(key = { "b)", "new" }, value = "Создание", group = GROUP)
    public String insert() {
        state = StatusType.INSERT;
        return "Режим Создания";
    }

    @ShellMethod(key = { "c)", "upd" }, value = "Обновление", group = GROUP)
    public String update() {
        state = StatusType.UPDATE;
        return "Режим Обновления";
    }

    @ShellMethod(key = { "d)", "del" }, value = "Удаление", group = GROUP)
    public String delete() {
        state = StatusType.DELETE;
        return "Режим Удаления";
    }

    enum EntityType {
        Author,
        Book,
        Genre
    }

    enum StatusType {
        INSERT,
        SELECT,
        UPDATE,
        DELETE,
        NONE
    }

    public Availability checkSelect() {
        return state.equals(StatusType.SELECT) ? Availability.available() : Availability.unavailable("Необходимо перейти в режим выборки");
    }
    public Availability checkInsert() {
        return state.equals(StatusType.INSERT) ? Availability.available() : Availability.unavailable("Необходимо перейти в режим создания");
    }
    public Availability checkUpdate() {
        return state.equals(StatusType.UPDATE) ? Availability.available() : Availability.unavailable("Необходимо перейти в режим обновления");
    }
    public Availability checkDelete() {
        return state.equals(StatusType.DELETE) ? Availability.available() : Availability.unavailable("Необходимо перейти в режим удаления");
    }

}
