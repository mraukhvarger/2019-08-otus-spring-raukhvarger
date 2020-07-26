package ru.otus.raukhvarger.homework_7.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.otus.raukhvarger.homework_7.jpa.entity.Book;

@Data
@Builder
@AllArgsConstructor
public class BookDTO {
    private Integer bookId;
    private String bookName;
    private AuthorDTO author;
    private GenreDTO genre;

    public Book buildJpaEntity() {
        return Book.builder()
                .bookId(bookId)
                .bookName(bookName)
                .authorId(author.getAuthorId())
                .genreId(genre.getGenreId())
                .build();
    }

    @Override
    public String toString() {
        return "BookDTO {" +
                "\n bookId = " + bookId +
                ",\n bookName = " + bookName +
                ",\n author = " + author.getAuthorName() +
                ",\n genre = " + genre.getGenreName() +
                "\n}";
    }
}
