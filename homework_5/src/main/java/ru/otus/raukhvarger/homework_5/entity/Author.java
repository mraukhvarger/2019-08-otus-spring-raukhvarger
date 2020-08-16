package ru.otus.raukhvarger.homework_5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private Integer authorId;
    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
    }

}
