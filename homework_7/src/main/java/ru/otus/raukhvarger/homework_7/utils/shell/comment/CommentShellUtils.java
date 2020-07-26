package ru.otus.raukhvarger.homework_7.utils.shell.comment;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.raukhvarger.homework_7.dto.BookDTO;
import ru.otus.raukhvarger.homework_7.dto.CommentDTO;
import ru.otus.raukhvarger.homework_7.service.BookProvider;
import ru.otus.raukhvarger.homework_7.service.CommentProvider;
import ru.otus.raukhvarger.homework_7.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_7.utils.messages.MessageProvider;

import java.util.List;

@Service
public class CommentShellUtils implements CommentShellUtilsProvider {
    private final IOProvider ioProvider;
    private final MessageProvider messageProvider;
    private final BookProvider bookProvider;
    private final CommentProvider commentProvider;

    public CommentShellUtils(IOProvider ioProvider, MessageProvider messageProvider,
                             BookProvider bookProvider, CommentProvider commentProvider) {
        this.ioProvider = ioProvider;
        this.messageProvider = messageProvider;
        this.bookProvider = bookProvider;
        this.commentProvider = commentProvider;
    }

    @Override
    public void create(Integer bookId) {
        BookDTO book = bookProvider.getById(bookId);
        if (book != null) {
            ioProvider.print(messageProvider.getMessage("HW.EnterComment"));
            boolean entered = false;
            while (!entered) {
                String commentText = ioProvider.read();
                if (StringUtils.isEmpty(commentText)) {
                    ioProvider.print(messageProvider.getMessage("HW.CommentNotEmpty"));
                } else {
                    entered = true;
                    CommentDTO comment = CommentDTO.builder()
                            .book(book)
                            .comment(commentText)
                            .build();
                    commentProvider.create(comment);
                    ioProvider.print(messageProvider.getFormattedMessage("HW.CommentAdded", book.getBookName()));
                }
            }
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
            return;
        }
    }

    @Override
    public void update(Integer commentId) {
        CommentDTO comment = commentProvider.getById(commentId);
        if (comment != null) {
            ioProvider.print(messageProvider.getMessage("HW.EnterNewComment"));
            boolean entered = false;
            while (!entered) {
                String commentText = ioProvider.read();
                if (StringUtils.isEmpty(commentText)) {
                    ioProvider.print(messageProvider.getMessage("HW.CommentNotEmpty"));
                } else {
                    entered = true;
                    comment.setComment(commentText);
                    commentProvider.update(comment);
                    ioProvider.print(messageProvider.getMessage("HW.CommentUpdated"));
                }
            }
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.CommentNotFoundById", commentId.toString()));
            return;
        }
    }

    @Override
    public void browseByBookId(Integer bookId) {
        if (bookProvider.getById(bookId) != null) {
            List<CommentDTO> bookComments = commentProvider.getByBookId(bookId);
            ioProvider.print(messageProvider.getMessage("HW.Comments"));
            bookComments.forEach(c -> ioProvider.print(c.toString()));
            ioProvider.print("==================================");
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
            return;
        }
    }

    @Override
    public void deleteById(Integer id) {
        CommentDTO comment = commentProvider.getById(id);
        if (comment != null) {
            commentProvider.deleteById(id);
            ioProvider.print(messageProvider.getFormattedMessage("HW.CommentDeleted", id.toString()));
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.CommentNotFoundById", id.toString()));
        }
    }

    @Override
    public void browse() {
        List<CommentDTO> comments = commentProvider.getAll();
        ioProvider.print(messageProvider.getMessage("HW.Comments"));
        comments.forEach(c -> ioProvider.print(c.toString()));
        ioProvider.print("==================================");
    }
}
