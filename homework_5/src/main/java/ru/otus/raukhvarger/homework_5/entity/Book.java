package ru.otus.raukhvarger.homework_5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private Integer bookId;
    private String bookName;
    private Author author;
    private Genre genre;

    public Book(String bookName) {
        this.bookName = bookName;
    }

}
