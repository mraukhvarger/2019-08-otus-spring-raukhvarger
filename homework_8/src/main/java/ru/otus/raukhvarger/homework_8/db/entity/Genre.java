package ru.otus.raukhvarger.homework_8.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import ru.otus.raukhvarger.homework_8.dto.GenreDTO;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "genres")
@Data
public class Genre {
    @Id
    private String genreId;

    @Field("genreName")
    private String genreName;

    public Genre(String genreName) {
        this.genreId = UUID.randomUUID().toString();
        this.genreName = genreName;
    }

    @Override
    public String toString() {
        return genreName;
    }

    @Override
    public boolean equals(Object genre) {
        return this.genreId.equals(((Genre) genre).getGenreId());
    }

    public GenreDTO buildDTO() {
        return GenreDTO.builder()
                .genreId(genreId)
                .genreName(genreName)
                .build();
    }
}
