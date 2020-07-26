package ru.otus.raukhvarger.homework_6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private Integer commentId;
    private BookDTO book;
    private String comment;

    public CommentDTO(BookDTO book, String comment) {
        this.book = book;
        this.comment = comment;
    }

    public CommentEntity buildJpaEntity() {
        return CommentEntity.builder()
                .commentId(commentId)
                .bookId(book.getBookId())
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
