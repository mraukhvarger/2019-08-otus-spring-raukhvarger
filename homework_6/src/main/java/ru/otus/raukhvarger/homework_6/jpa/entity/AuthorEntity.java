package ru.otus.raukhvarger.homework_6.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TABLE_AUTHOR")
public class AuthorEntity {
    @Id
    @Column(name = "AUTHORID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer authorId;

    @Column(name = "AUTHORNAME", length = 1024)
    private String authorName;

    @OneToMany(targetEntity = BookEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHORID", insertable = false, updatable = false)
    private Set<BookEntity> bookEntities;

    public AuthorDTO buildDTO() {
        return AuthorDTO.builder()
                .authorId(authorId)
                .authorName(authorName)
                .build();
    }
}
