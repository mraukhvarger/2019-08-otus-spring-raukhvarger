package ru.otus.raukhvarger.homework_8.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.raukhvarger.homework_8.dto.CommentDTO;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "comments")
@Data
public class Comment {
    @Id
    private String commentId;

    @Field("comment")
    private String comment;

    public Comment(String comment) {
        this.commentId = UUID.randomUUID().toString();
        this.comment = comment;
    }

    public CommentDTO buildDTO() {
        return CommentDTO.builder()
                .commentId(commentId)
                .comment(comment)
                .build();
    }
}
