package ru.otus.raukhvarger.homework_8.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.raukhvarger.homework_8.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "books")
@Data
public class Book {
    @Id
    private String bookId;

    @Field("bookName")
    private String bookName;

    @DBRef
    private Author author;

    @DBRef
    private Genre genre;

    @DBRef
    private List<Comment> comments = new ArrayList<>();

    public Book(String bookName, Author author, Genre genre) {
        this.bookId = UUID.randomUUID().toString();
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
    }

    public Book(String bookName, Author author, Genre genre, List<Comment> comments) {
        this(bookName, author, genre);
        this.comments = comments;
    }

    public Book(String bookName, Author author) {
        this.bookId = UUID.randomUUID().toString();
        this.bookName = bookName;
        this.author = author;
    }

    public BookDTO buildDTO() {
        return BookDTO.builder()
                .bookId(bookId)
                .bookName(bookName)
                .author(author.buildDTO())
                .genre(genre.buildDTO())
                .build();
    }
}
