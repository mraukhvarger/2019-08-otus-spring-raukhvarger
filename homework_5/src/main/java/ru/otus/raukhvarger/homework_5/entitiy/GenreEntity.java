package ru.otus.raukhvarger.homework_5.entitiy;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
//@RequiredArgsConstructor
public class GenreEntity {

    Long id;

    String genre;

    @ToString.Exclude
    List<BookEntity> books = new ArrayList<>();

}
