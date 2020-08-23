package ru.otus.raukhvarger.homework_6.utils.shell.book;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;
import ru.otus.raukhvarger.homework_6.dto.BookDTO;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;
import ru.otus.raukhvarger.homework_6.service.AuthorProvider;
import ru.otus.raukhvarger.homework_6.service.BookProvider;
import ru.otus.raukhvarger.homework_6.service.GenreProvider;
import ru.otus.raukhvarger.homework_6.utils.io.IOProvider;
import ru.otus.raukhvarger.homework_6.utils.messages.MessageProvider;

import java.util.List;

@Qualifier("BookShellUtilsImpl")
@Service
public class BookShellUtilsImpl implements BookShellUtilsProvider {
    private final MessageProvider messageProvider;
    private final IOProvider ioProvider;
    private final BookProvider bookProvider;
    private final GenreProvider genreProvider;
    private final AuthorProvider authorProvider;

    public BookShellUtilsImpl(MessageProvider messageProvider, IOProvider ioProvider,
                              BookProvider bookProvider, GenreProvider genreProvider, AuthorProvider authorProvider) {
        this.messageProvider = messageProvider;
        this.ioProvider = ioProvider;
        this.bookProvider = bookProvider;
        this.genreProvider = genreProvider;
        this.authorProvider = authorProvider;
    }

    @Override
    public void create() {
        AuthorDTO author = getAuthorForCreate();
        GenreDTO genre = getGenreForCreate();
        BookDTO book = getBookForCreate();
        book.setAuthor(author);
        book.setGenre(genre);
        bookProvider.create(book);
        ioProvider.print(messageProvider.getFormattedMessage("HW.BookCreated", book.getBookName()));
    }

    @Override
    public void getByName(String bookName) {
        List<BookDTO> books = bookProvider.getByName(bookName);
        if (books.size() > 0) {
            books.forEach(b -> ioProvider.print(b.toString()));
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundByName", bookName));
        }
    }

    @Override
    public void getById(Long bookId) {
        BookDTO book = bookProvider.getById(bookId);
        if (book != null) {
            ioProvider.print(book.toString());
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
        }
    }

    @Override
    public void browse() {
        List<BookDTO> books = bookProvider.getAll();
        ioProvider.print(messageProvider.getMessage("HW.Books"));
        books.forEach(b -> ioProvider.print(b.toString()));
        ioProvider.print("==================================");
    }

    @Override
    public void deleteById(Long bookId) {
        BookDTO book = bookProvider.getById(bookId);
        if (book != null) {
            bookProvider.deleteById(bookId);
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookDeleted", bookId.toString()));
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
        }
    }

    @Override
    public void updateById(Long bookId) {
        BookDTO book = bookProvider.getById(bookId);
        if (book != null) {
            GenreDTO genre = getGenreForUpdate();
            AuthorDTO author = getAuthorForUpdate();
            String bookName = getBookNameForUpdate();
            if (genre != null || author != null || bookName != null) {
                if (author != null) book.setAuthor(author);
                if (genre != null) book.setGenre(genre);
                if (bookName != null) book.setBookName(bookName);
                bookProvider.update(book);
                ioProvider.print(messageProvider.getFormattedMessage("HW.BookUpdated", bookId.toString()));
            } else {
                ioProvider.print(messageProvider.getFormattedMessage("HW.BookNothingToUpdate", bookId.toString()));
            }
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BookNotFoundById", bookId.toString()));
        }
    }

    @Override
    public void getByAuthorName(String authorName) {
        AuthorDTO author = authorProvider.getByNameWithBooks(authorName);
        if (author == null) {
            ioProvider.print(messageProvider.getFormattedMessage("HW.AuthorNotFound", authorName));
            return;
        }
        if (author.getBooks() != null && author.getBooks().size() > 0) {
            ioProvider.print(messageProvider.getMessage("HW.Books"));
            author.getBooks().forEach(b -> ioProvider.print(b.toString()));
            ioProvider.print("==================================");
        } else {
            ioProvider.print(messageProvider.getFormattedMessage("HW.BooksNotFoundByAuthorName", authorName));
        }
    }

    private BookDTO getBookForCreate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterBookName"));
        boolean entered = false;
        while (!entered) {
            String bookName = ioProvider.read();
            if (StringUtils.isEmpty(bookName)) {
                ioProvider.print(messageProvider.getMessage("HW.BookNameNotEmpty"));
            } else {
                BookDTO book = new BookDTO(bookName);
                return book;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    private GenreDTO getGenreForCreate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterGenreName"));
        boolean entered = false;
        while (!entered) {
            String genreName = ioProvider.read();
            if (StringUtils.isEmpty(genreName)) {
                ioProvider.print(messageProvider.getMessage("HW.GenreNameNotEmpty"));
            } else {
                GenreDTO genre = genreProvider.getOrCreateByName(genreName);
                return genre;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    private AuthorDTO getAuthorForCreate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterAuthorName"));
        boolean entered = false;
        while (!entered) {
            String authorName = ioProvider.read();
            if (StringUtils.isEmpty(authorName)) {
                ioProvider.print(messageProvider.getMessage("HW.AuthorNameNotEmpty"));
            } else {
                AuthorDTO author = authorProvider.getOrCreateByName(authorName);
                return author;
            }
        }
        throw new RuntimeException(messageProvider.getMessage("HW.DataEntryError"));
    }

    private String getBookNameForUpdate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterBookName"));
        boolean entered = false;
        while (!entered) {
            String bookName = ioProvider.read();
            if (!StringUtils.isEmpty(bookName)) {
                return bookName;
            } else {
                entered = true;
            }
        }
        return null;
    }

    private GenreDTO getGenreForUpdate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterGenreName"));
        boolean entered = false;
        while (!entered) {
            String genreName = ioProvider.read();
            if (!StringUtils.isEmpty(genreName)) {
                GenreDTO genre = genreProvider.getOrCreateByName(genreName);
                return genre;
            } else {
                entered = true;
            }
        }
        return null;
    }

    private AuthorDTO getAuthorForUpdate() {
        ioProvider.print(messageProvider.getMessage("HW.EnterAuthorName"));
        boolean entered = false;
        while (!entered) {
            String authorName = ioProvider.read();
            if (!StringUtils.isEmpty(authorName)) {
                AuthorDTO author = authorProvider.getOrCreateByName(authorName);
                return author;
            } else {
                entered = true;
            }
        }
        return null;
    }
}
