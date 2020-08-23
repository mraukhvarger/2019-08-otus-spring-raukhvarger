package ru.otus.raukhvarger.homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.otus.raukhvarger.homework_8.db.entity.Comment;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CommentDTO {
    private String commentId;
    private BookDTO book;
    private String comment;

    public Comment buildDBEntity() {
        return Comment.builder()
                .commentId(commentId != null ? commentId : UUID.randomUUID().toString())
                .comment(comment)
                .build();
    }

    @Override
    public String toString() {
        return "CommentDTO {" +
                "\n commentId = " + commentId +
                ",\n comment = " + comment +
                "\n}";
    }
}
