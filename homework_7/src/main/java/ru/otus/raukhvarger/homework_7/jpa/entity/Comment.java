package ru.otus.raukhvarger.homework_7.jpa.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import ru.otus.raukhvarger.homework_7.dto.CommentDTO;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"book"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_COMMENT")
public class Comment {
    @Id
    @Column(name = "COMMENTID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = "COMMENT", length = 4000)
    private String comment;

    @Column(name = "BOOKID")
    private Integer bookId;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOKID", insertable = false, updatable = false)
    @BatchSize(size = 10)
    private Book book;

    public CommentDTO buildDTO() {
        return CommentDTO.builder()
                .commentId(commentId)
                .comment(comment)
                .book(book.buildDTO())
                .build();
    }
}
