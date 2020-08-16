package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.StringUtils;
import ru.otus.raukhvarger.homework_5.entity.Genre;
import ru.otus.raukhvarger.homework_5.service.genre.GenreProvider;
import ru.otus.raukhvarger.homework_5.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_5.utils.messages.MessageProvider;

@ShellComponent
public class GenreShellCommander {
    private final MessageProvider messageProvider;
    private final IOProvider ioProvider;
    private final GenreProvider genreProvider;

    public GenreShellCommander(MessageProvider messageProvider, IOProvider ioProvider, GenreProvider genreProvider) {
        this.messageProvider = messageProvider;
        this.ioProvider = ioProvider;
        this.genreProvider = genreProvider;
    }

    @ShellMethod(key = {"gc", "gcr"}, value = "Create genre")
    public void createGenre() {
        ioProvider.print(messageProvider.getMessage("HW.EnterGenreName"));
        boolean entered = false;
        while (!entered) {
            String genreName = ioProvider.read();
            if (StringUtils.isEmpty(genreName)) {
                ioProvider.print(messageProvider.getMessage("HW.GenreNameNotEmpty"));
            } else if (genreExists(genreName)) {
                ioProvider.print(messageProvider.getMessage("HW.GenreAlreadyExists"));
                return;
            } else {
                entered = true;
                Genre genre = new Genre(null, genreName);
                genreProvider.createGenre(genre);
                ioProvider.print(messageProvider.getFormattedMessage("HW.GenreCreated", genreName));
            }
        }
    }

    private boolean genreExists(String genreName) {
        return genreProvider.getGenreByName(genreName) != null;
    }
}
