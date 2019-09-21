package ru.otus.raukhvarger.homework_5.entitiy;

import lombok.*;
import ru.otus.raukhvarger.homework_5.annotation.Field;
import ru.otus.raukhvarger.homework_5.annotation.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "authors_and_books_table")
public class AuthorAndBookEntity {

    @Field(name = "id")
    Integer id;

    @NonNull
    @Field(name = "id_author")
    Integer idAuthor;

    @NonNull
    @Field(name = "id_book")
    Integer idBook;

}
