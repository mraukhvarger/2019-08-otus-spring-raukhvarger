package ru.otus.raukhvarger.homework_5.entitiy;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AuthorEntity {

    Long id;

    String firstName;

    String lastName;

    @ToString.Exclude
    List<BookEntity> books = new ArrayList<>();

}
