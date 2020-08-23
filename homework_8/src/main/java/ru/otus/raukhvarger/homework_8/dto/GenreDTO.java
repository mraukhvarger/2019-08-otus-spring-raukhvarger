package ru.otus.raukhvarger.homework_8.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.otus.raukhvarger.homework_8.db.entity.Genre;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class GenreDTO {
    private String genreId;
    private String genreName;

    public Genre buildDBEntity() {
        return Genre.builder()
                .genreId(genreId != null ? genreId : UUID.randomUUID().toString())
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
