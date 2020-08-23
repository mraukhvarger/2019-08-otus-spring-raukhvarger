package ru.otus.raukhvarger.homework_6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.raukhvarger.homework_6.jpa.entity.BookEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private Long bookId;
    private String bookName;
    private AuthorDTO author;
    private GenreDTO genre;

    public BookDTO(String bookName) {
        this.bookName = bookName;
    }

    public BookEntity buildJpaEntity() {
        return BookEntity.builder()
                .bookId(bookId)
                .bookName(bookName)
                .authorEntity(author.buildJpaEntity())
                .genreEntity(genre.buildJpaEntity())
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
