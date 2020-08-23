package ru.otus.raukhvarger.homework_6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;
import ru.otus.raukhvarger.homework_6.utils.EntityConverter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Long commentId;
    private BookDTO book;
    private String comment;

    public CommentDTO(BookDTO book, String comment) {
        this.book = book;
        this.comment = comment;
    }

    public CommentEntity buildJpaEntity() {
        return CommentEntity.builder()
                .commentId(commentId)
                .bookEntity(book.buildJpaEntity())
                .comment(comment)
                .build();
    }

    @Override
    public String toString() {
        return "CommentDTO {" +
                "\n commentId = " + commentId +
                ",\n bookId = " + book.getBookId() +
                ",\n book = " + book.getBookName() +
                ",\n comment = " + comment +
                "\n}";
    }
}
