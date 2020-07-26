package ru.otus.raukhvarger.homework_5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreEntity {
    private Integer genreId;
    private String genreName;

    public GenreEntity(String genreName) {
        this.genreName = genreName;
    }

    public Map convertToMap() {
        Map genreMap = new HashMap<>();
        if (genreId != null) {
            genreMap.put("genreId", genreId);
        }
        genreMap.put("genreName", genreName);
        return genreMap;
    }
}
