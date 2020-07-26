package ru.otus.raukhvarger.homework_7.jpa.entity;

import lombok.*;
import ru.otus.raukhvarger.homework_7.dto.BookDTO;

import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode(exclude = {"author", "genre"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_BOOK")
public class Book {
    @Id
    @Column(name = "BOOKID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;

    @Column(name = "BOOKNAME")
    private String bookName;

    @Column(name = "AUTHORID")
    private Integer authorId;

    @Column(name = "GENREID")
    private Integer genreId;

    @OneToOne(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHORID", insertable = false, updatable = false)
    private Author author;

    @OneToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "GENREID", insertable = false, updatable = false)
    private Genre genre;

    public BookDTO buildDTO() {
        return BookDTO.builder()
                .bookId(bookId)
                .bookName(bookName)
                .author(author.buildDTO())
                .genre(genre.buildDTO())
                .build();
    }
}
