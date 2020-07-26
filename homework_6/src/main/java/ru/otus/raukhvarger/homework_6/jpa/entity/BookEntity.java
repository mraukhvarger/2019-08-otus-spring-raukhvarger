package ru.otus.raukhvarger.homework_6.jpa.entity;

import lombok.*;
import ru.otus.raukhvarger.homework_6.dto.BookDTO;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"authorEntity", "genreEntity"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TABLE_BOOK")
public class BookEntity {
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

    @OneToOne(targetEntity = AuthorEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHORID", insertable = false, updatable = false)
    private AuthorEntity authorEntity;

    @OneToOne(targetEntity = GenreEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "GENREID", insertable = false, updatable = false)
    private GenreEntity genreEntity;

    public BookDTO buildDTO() {
        return BookDTO.builder()
                .bookId(bookId)
                .bookName(bookName)
                .author(authorEntity.buildDTO())
                .genre(genreEntity.buildDTO())
                .build();
    }
}
