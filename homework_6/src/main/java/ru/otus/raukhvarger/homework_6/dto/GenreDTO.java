package ru.otus.raukhvarger.homework_6.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenreDTO {
    private Long genreId;
    private String genreName;

    public GenreDTO(String genreName) {
        this.genreName = genreName;
    }

    public GenreEntity buildJpaEntity() {
        return GenreEntity.builder()
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
