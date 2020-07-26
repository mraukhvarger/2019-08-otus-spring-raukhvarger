package ru.otus.raukhvarger.homework_6.utils.shell.genre;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;
import ru.otus.raukhvarger.homework_6.service.GenreProvider;
import ru.otus.raukhvarger.homework_6.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_6.utils.messages.MessageProvider;
import ru.otus.raukhvarger.homework_6.utils.shell.ShellUtilsProvider;

import java.util.List;

@Qualifier("GenreShellUtils")
@Service
public class GenreShellUtils implements ShellUtilsProvider {
    private final IOProvider ioProvider;
    private final MessageProvider messageProvider;
    private final GenreProvider genreProvider;

    public GenreShellUtils(IOProvider ioProvider, MessageProvider messageProvider,
                           GenreProvider genreProvider) {
        this.ioProvider = ioProvider;
        this.messageProvider = messageProvider;
        this.genreProvider = genreProvider;
    }

    @Override
    public void create() {
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
                GenreDTO genre = new GenreDTO(null, genreName);
                genreProvider.create(genre);
                ioProvider.print(messageProvider.getFormattedMessage("HW.GenreCreated", genreName));
            }
        }
    }

    private boolean genreExists(String genreName) {
        return genreProvider.getByName(genreName) != null;
    }

    @Override
    public void update() {
        ioProvider.print(messageProvider.getMessage("HW.EnterGenreName"));
        boolean entered = false;
        while (!entered) {
            String genreName = ioProvider.read();
            if (StringUtils.isEmpty(genreName)) {
                ioProvider.print(messageProvider.getMessage("HW.GenreNameNotEmpty"));
            } else {
                GenreDTO genre = genreProvider.getByName(genreName);
                if (genre != null) {
                    genre.setGenreName(enterNewGenreName());
                    genreProvider.update(genre);
                    ioProvider.print(messageProvider.getMessage("HW.GenreUpdated"));
                    return;
                } else {
                    ioProvider.print(messageProvider.getFormattedMessage("HW.GenreNotFound", genreName));
                    return;
                }
            }
        }
    }

    private String enterNewGenreName() {
        ioProvider.print(messageProvider.getMessage("HW.EnterNewGenreName"));
        boolean entered = false;
        while (!entered) {
            String newGenreName = ioProvider.read();
            if (StringUtils.isEmpty(newGenreName)) {
                ioProvider.print(messageProvider.getMessage("HW.GenreNameNotEmpty"));
            } else {
                return newGenreName;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    @Override
    public void deleteById(Integer id) {
        GenreDTO genre = genreProvider.getById(id);
        if (genre != null) {
            genreProvider.deleteById(id);
            ioProvider.print(messageProvider.getFormattedMessage("HW.GenreDeleted", id.toString()));
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.GenreNotFoundById", id.toString()));
        }
    }

    @Override
    public void browse() {
        List<GenreDTO> genres = genreProvider.getAll();
        ioProvider.print(messageProvider.getMessage("HW.Genres"));
        genres.forEach(b -> ioProvider.print(b.toString()));
        ioProvider.print("==================================");
    }
}
