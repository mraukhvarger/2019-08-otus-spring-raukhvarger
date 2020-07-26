package ru.otus.raukhvarger.homework_7.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.otus.raukhvarger.homework_7.jpa.entity.Comment;

@Data
@Builder
@AllArgsConstructor
public class CommentDTO {
    private Integer commentId;
    private BookDTO book;
    private String comment;

    public Comment buildJpaEntity() {
        return Comment.builder()
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
