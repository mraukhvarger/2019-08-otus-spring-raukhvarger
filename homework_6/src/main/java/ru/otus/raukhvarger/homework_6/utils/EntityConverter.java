package ru.otus.raukhvarger.homework_6.utils;

import org.springframework.stereotype.Component;
import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;
import ru.otus.raukhvarger.homework_6.dto.BookDTO;
import ru.otus.raukhvarger.homework_6.dto.CommentDTO;
import ru.otus.raukhvarger.homework_6.dto.GenreDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;
import ru.otus.raukhvarger.homework_6.jpa.entity.BookEntity;
import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;
import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;

@Component
public class EntityConverter {

    public AuthorDTO buildDTO(AuthorEntity authorEntity) {
        return AuthorDTO.builder()
                .authorId(authorEntity.getAuthorId())
                .authorName(authorEntity.getAuthorName())
                .build();
    }

    public BookDTO buildDTO(BookEntity bookEntity) {
        return BookDTO.builder()
                .bookId(bookEntity.getBookId())
                .bookName(bookEntity.getBookName())
                .author(buildDTO(bookEntity.getAuthorEntity()))
                .genre(buildDTO(bookEntity.getGenreEntity()))
                .build();
    }

    public CommentDTO buildDTO(CommentEntity commentEntity) {
        return CommentDTO.builder()
                .commentId(commentEntity.getCommentId())
                .comment(commentEntity.getComment())
                .book(buildDTO(commentEntity.getBookEntity()))
                .build();
    }

    public GenreDTO buildDTO(GenreEntity genreEntity) {
        return GenreDTO.builder()
                .genreId(genreEntity.getGenreId())
                .genreName(genreEntity.getGenreName())
                .build();
    }

}
