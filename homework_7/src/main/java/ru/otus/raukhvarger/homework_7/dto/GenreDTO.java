package ru.otus.raukhvarger.homework_7.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.otus.raukhvarger.homework_7.jpa.entity.Genre;

@Data
@Builder
@AllArgsConstructor
public class GenreDTO {
    private Integer genreId;
    private String genreName;

    public Genre buildJpaEntity() {
        return Genre.builder()
                .genreId(genreId)
                .genreName(genreName)
                .build();
    }

    @Override
    public String toString() {
        return "GenreDTO {" +
                "\n genreId = " + genreId +
                ",\n genreName = " + genreName +
                "\n}";
    }
}
