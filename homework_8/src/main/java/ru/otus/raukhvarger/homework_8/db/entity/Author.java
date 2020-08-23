package ru.otus.raukhvarger.homework_8.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.raukhvarger.homework_8.dto.AuthorDTO;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "authors")
@Data
public class Author {
    @Id
    private String authorId;

    @Field("authorName")
    private String authorName;

    public Author(String authorName) {
        this.authorId = UUID.randomUUID().toString();
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return authorName;
    }

    @Override
    public boolean equals(Object author) {
        return this.authorId.equals(((Author) author).getAuthorId());
    }

    public AuthorDTO buildDTO() {
        return AuthorDTO.builder()
                .authorId(authorId)
                .authorName(authorName)
                .build();
    }
}
