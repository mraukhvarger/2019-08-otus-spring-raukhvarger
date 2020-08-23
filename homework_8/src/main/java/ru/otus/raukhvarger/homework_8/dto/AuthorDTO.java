package ru.otus.raukhvarger.homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.otus.raukhvarger.homework_8.db.entity.Author;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class AuthorDTO {
    private String authorId;
    private String authorName;
    private Set<BookDTO> books;

    public Author buildDBEntity() {
        return Author.builder()
                .authorId(authorId != null ? authorId : UUID.randomUUID().toString())
                .authorName(authorName)
                .build();
    }

    @Override
    public String toString() {
        return "AuthorDTO {" +
                "\n authorId = " + authorId +
                ",\n authorName = " + authorName +
                "\n}";
    }
}
