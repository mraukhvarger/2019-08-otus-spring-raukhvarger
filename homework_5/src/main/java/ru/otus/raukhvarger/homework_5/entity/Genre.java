package ru.otus.raukhvarger.homework_5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    private Integer genreId;
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

}
