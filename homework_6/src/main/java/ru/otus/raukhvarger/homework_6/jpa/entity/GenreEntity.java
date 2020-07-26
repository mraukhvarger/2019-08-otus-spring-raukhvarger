package ru.otus.raukhvarger.homework_6.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TABLE_GENRE")
public class GenreEntity {
    @Id
    @Column(name = "GENREID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    @Column(name = "GENRENAME")
    private String genreName;

    public GenreDTO buildDTO() {
        return GenreDTO.builder()
                .genreId(genreId)
                .genreName(genreName)
                .build();
    }
}
