package ru.otus.raukhvarger.homework_6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    private Integer authorId;
    private String authorName;
    private Set<BookDTO> books;

    public AuthorDTO(String authorName) {
        this.authorName = authorName;
    }

    public AuthorDTO(Integer authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public AuthorEntity buildJpaEntity() {
        return AuthorEntity.builder()
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
