package ru.otus.raukhvarger.homework_5.entitiy;

import lombok.*;
import ru.otus.raukhvarger.homework_5.annotation.Field;
import ru.otus.raukhvarger.homework_5.annotation.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "authors_table")
public class AuthorEntity {

    @Field(name = "id")
    Integer id;

    @NonNull
    @Field(name = "first_name")
    String firstName;

    @NonNull
    @Field(name = "last_name")
    String lastName;

}
