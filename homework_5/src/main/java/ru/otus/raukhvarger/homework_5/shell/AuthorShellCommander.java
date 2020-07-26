package ru.otus.raukhvarger.homework_5.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.StringUtils;
import ru.otus.raukhvarger.homework_5.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_5.service.author.AuthorProvider;
import ru.otus.raukhvarger.homework_5.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_5.utils.messages.MessageProvider;

@ShellComponent
public class AuthorShellCommander {
    private final MessageProvider messageProvider;
    private final IOProvider ioProvider;
    private final AuthorProvider authorProvider;

    public AuthorShellCommander(MessageProvider messageProvider, IOProvider ioProvider, AuthorProvider authorProvider) {
        this.messageProvider = messageProvider;
        this.ioProvider = ioProvider;
        this.authorProvider = authorProvider;
    }

    @ShellMethod(key = {"ac", "acr"}, value = "Create author")
    public void createAuthor() {
        ioProvider.print(messageProvider.getMessage("HW.EnterAuthorName"));
        boolean entered = false;
        while (!entered) {
            String authorName = ioProvider.read();
            if (StringUtils.isEmpty(authorName)) {
                ioProvider.print(messageProvider.getMessage("HW.AuthorNameNotEmpty"));
            } else if (authorExists(authorName)) {
                ioProvider.print(messageProvider.getMessage("HW.AuthorAlreadyExists"));
                return;
            } else {
                entered = true;
                AuthorEntity authorEntity = new AuthorEntity(null, authorName);
                authorProvider.createAuthor(authorEntity);
                ioProvider.print(messageProvider.getFormattedMessage("HW.AuthorCreated", authorName));
            }
        }
    }

    private boolean authorExists(String authorName) {
        return authorProvider.getAuthorByName(authorName) != null;
    }
}
