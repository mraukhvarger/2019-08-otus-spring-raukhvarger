package ru.otus.raukhvarger.homework_5.entitiy;

import lombok.*;
import ru.otus.raukhvarger.homework_5.annotation.Field;
import ru.otus.raukhvarger.homework_5.annotation.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "books_table")
public class BookEntity {

    @Field(name = "id")
    Integer id;

    @Field(name = "id_genre")
    Integer idGenre = null;

    @NonNull
    @Field(name = "caption")
    String caption;

}
