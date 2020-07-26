package ru.otus.raukhvarger.homework_7.jpa.entity;

import lombok.*;
import ru.otus.raukhvarger.homework_7.dto.AuthorDTO;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"books"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TABLE_AUTHOR")
public class Author {
    @Id
    @Column(name = "AUTHORID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(name = "AUTHORNAME", length = 1024)
    private String authorName;

    @OneToMany(targetEntity = Book.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHORID", insertable = false, updatable = false)
    private Set<Book> books;

    public AuthorDTO buildDTO() {
        return AuthorDTO.builder()
                .authorId(authorId)
                .authorName(authorName)
                .build();
    }
}
