package ru.otus.raukhvarger.homework_7.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.otus.raukhvarger.homework_7.jpa.entity.Author;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AuthorDTO {
    private Integer authorId;
    private String authorName;
    private Set<BookDTO> books;

    public Author buildJpaEntity() {
        return Author.builder()
                .authorId(authorId)
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
