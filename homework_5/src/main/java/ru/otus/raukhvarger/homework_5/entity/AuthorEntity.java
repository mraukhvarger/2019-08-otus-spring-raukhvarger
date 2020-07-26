package ru.otus.raukhvarger.homework_5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorEntity {
    private Integer authorId;
    private String authorName;

    public AuthorEntity(String authorName) {
        this.authorName = authorName;
    }

    public Map convertToMap() {
        Map authorMap = new HashMap<>();
        if (authorId != null) {
            authorMap.put("authorId", authorId);
        }
        authorMap.put("authorName", authorName);
        return authorMap;
    }
}
