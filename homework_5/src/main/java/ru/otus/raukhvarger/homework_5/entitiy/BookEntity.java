package ru.otus.raukhvarger.homework_5.entitiy;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@Builder
@ToString
public class BookEntity {

    Long id;

    GenreEntity genre;

    String caption;

    @ToString.Exclude
    List<AuthorEntity> authors = new ArrayList<>();

}
