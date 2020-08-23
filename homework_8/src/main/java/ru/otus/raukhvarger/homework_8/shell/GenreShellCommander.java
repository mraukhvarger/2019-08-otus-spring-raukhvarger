package ru.otus.raukhvarger.homework_8.shell;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.raukhvarger.homework_8.utils.shell.ShellUtilsProvider;

@ShellComponent
public class GenreShellCommander {
    private final ShellUtilsProvider provider;

    public GenreShellCommander(@Qualifier("GenreShellUtils") ShellUtilsProvider provider) {
        this.provider = provider;
    }

    @ShellMethod(key = {"gc", "gcr"}, value = "Create genre")
    public void createGenre() {
        provider.create();
    }

    @ShellMethod(key = {"gu", "gupd"}, value = "Update genre")
    public void updateGenre() {
        provider.update();
    }

    @ShellMethod(key = {"gd", "gdel"}, value = "Delete genre by id")
    public void deleteGenre(String genreId) {
        provider.deleteById(genreId);
    }

    @ShellMethod(key = {"gb", "gl", "glist"}, value = "Browse all genres")
    public void listGenres() {
        provider.browse();
    }
}
