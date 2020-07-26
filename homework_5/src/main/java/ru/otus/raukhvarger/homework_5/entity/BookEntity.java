package ru.otus.raukhvarger.homework_5.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookEntity {
    private Integer bookId;
    private String bookName;
    private AuthorEntity authorEntity;
    private GenreEntity genreEntity;

    public BookEntity(String bookName) {
        this.bookName = bookName;
    }

    public Map convertToMap() {
        Map bookMap = new HashMap<>();
        if (bookId != null) {
            bookMap.put("bookId", bookId);
        }
        bookMap.put("bookName", bookName);
        bookMap.put("authorId", authorEntity.getAuthorId());
        bookMap.put("genreId", genreEntity.getGenreId());
        return bookMap;
    }
}
