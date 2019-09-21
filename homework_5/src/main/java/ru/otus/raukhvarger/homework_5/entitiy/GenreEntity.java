package ru.otus.raukhvarger.homework_5.entitiy;

import lombok.*;
import ru.otus.raukhvarger.homework_5.annotation.Field;
import ru.otus.raukhvarger.homework_5.annotation.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "genres_table")
public class GenreEntity {

    @Field(name = "id")
    Integer id;

    @NonNull
    @Field(name = "genre")
    String genre;

}
