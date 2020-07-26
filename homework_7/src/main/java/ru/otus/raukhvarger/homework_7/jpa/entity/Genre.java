package ru.otus.raukhvarger.homework_7.jpa.entity;

import lombok.*;
import ru.otus.raukhvarger.homework_7.dto.GenreDTO;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_GENRE")
public class Genre {
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
