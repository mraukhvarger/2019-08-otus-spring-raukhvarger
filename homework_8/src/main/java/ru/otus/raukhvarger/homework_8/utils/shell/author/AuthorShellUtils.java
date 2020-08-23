package ru.otus.raukhvarger.homework_8.utils.shell.author;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.raukhvarger.homework_8.dto.AuthorDTO;
import ru.otus.raukhvarger.homework_8.service.author.AuthorProvider;
import ru.otus.raukhvarger.homework_8.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_8.utils.messages.MessageProvider;
import ru.otus.raukhvarger.homework_8.utils.shell.ShellUtilsProvider;

import java.util.List;

@Qualifier("AuthorShellUtils")
@Service
public class AuthorShellUtils implements ShellUtilsProvider {

    private final IOProvider ioProvider;
    private final MessageProvider messageProvider;
    private final AuthorProvider authorProvider;

    public AuthorShellUtils(IOProvider ioProvider, MessageProvider messageProvider,
                            AuthorProvider authorProvider) {
        this.ioProvider = ioProvider;
        this.messageProvider = messageProvider;
        this.authorProvider = authorProvider;
    }

    @Override
    public void create() {
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
                AuthorDTO author = AuthorDTO.builder().authorName(authorName).build();
                authorProvider.create(author);
                ioProvider.print(messageProvider.getFormattedMessage("HW.AuthorCreated", authorName));
            }
        }
    }

    private boolean authorExists(String authorName) {
        return authorProvider.getByName(authorName) != null;
    }

    @Override
    public void update() {
        ioProvider.print(messageProvider.getMessage("HW.EnterAuthorName"));
        boolean entered = false;
        while (!entered) {
            String authorName = ioProvider.read();
            if (StringUtils.isEmpty(authorName)) {
                ioProvider.print(messageProvider.getMessage("HW.AuthorNameNotEmpty"));
            } else {
                AuthorDTO author = authorProvider.getByName(authorName);
                if (author != null) {
                    author.setAuthorName(enterNewAuthorName());
                    authorProvider.update(author);
                    ioProvider.print(messageProvider.getMessage("HW.AuthorUpdated"));
                    return;
                } else {
                    ioProvider.print(messageProvider.getFormattedMessage("HW.AuthorNotFound", authorName));
                    return;
                }
            }
        }
    }

    private String enterNewAuthorName() {
        ioProvider.print(messageProvider.getMessage("HW.EnterNewAuthorName"));
        boolean entered = false;
        while (!entered) {
            String newAuthorName = ioProvider.read();
            if (StringUtils.isEmpty(newAuthorName)) {
                ioProvider.print(messageProvider.getMessage("HW.AuthorNameNotEmpty"));
            } else {
                return newAuthorName;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    @Override
    public void deleteById(String id) {
        AuthorDTO author = authorProvider.getById(id);
        if (author != null) {
            authorProvider.deleteById(id);
            ioProvider.print(messageProvider.getFormattedMessage("HW.AuthorDeleted", id.toString()));
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.AuthorNotFoundById", id.toString()));
        }
    }

    @Override
    public void browse() {
        List<AuthorDTO> authors = authorProvider.getAll();
        ioProvider.print(messageProvider.getMessage("HW.Authors"));
        authors.forEach(b -> ioProvider.print(b.toString()));
        ioProvider.print("==================================");
    }
}
