package ru.otus.raukhvarger.homework_6.shell;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.raukhvarger.homework_6.utils.shell.ShellUtilsProvider;

@ShellComponent
public class AuthorShellCommander {
    private final ShellUtilsProvider provider;

    public AuthorShellCommander(@Qualifier("AuthorShellUtils") ShellUtilsProvider provider) {
        this.provider = provider;
    }

    @ShellMethod(key = {"ac", "acr"}, value = "Create author")
    public void createAuthor() {
        provider.create();
    }

    @ShellMethod(key = {"au", "aupd"}, value = "Update author")
    public void updateAuthor() {
        provider.update();
    }

    @ShellMethod(key = {"ad", "adel"}, value = "Delete author by id")
    public void deleteAuthor(Long authorId) {
        provider.deleteById(authorId);
    }

    @ShellMethod(key = {"ab", "al", "alist"}, value = "Browse all authors")
    public void listAuthors() {
        provider.browse();
    }

}
