package ru.otus.raukhvarger.homework_6.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import ru.otus.raukhvarger.homework_6.dto.CommentDTO;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TABLE_COMMENT")
public class CommentEntity {
    @Id
    @Column(name = "COMMENTID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "COMMENT", length = 4000)
    private String comment;

    @Column(name = "BOOKID")
    private Long bookId;

    @ManyToOne(targetEntity = BookEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "BOOKID", insertable = false, updatable = false)
    @BatchSize(size = 10)
    private BookEntity bookEntity;

}
