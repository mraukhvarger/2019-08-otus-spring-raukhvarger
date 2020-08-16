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
    private Long bookId;

    @Column(name = "BOOKNAME")
    private String bookName;

    @Column(name = "AUTHORID")
    private Long authorId;

    @Column(name = "GENREID")
    private Long genreId;

    @ManyToOne(targetEntity = AuthorEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHORID", insertable = false, updatable = false)
    private AuthorEntity authorEntity;

    @ManyToOne(targetEntity = GenreEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "GENREID", insertable = false, updatable = false)
    private GenreEntity genreEntity;

}
