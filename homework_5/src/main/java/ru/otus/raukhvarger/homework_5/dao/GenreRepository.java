package ru.otus.raukhvarger.homework_5.dao;

import ru.otus.raukhvarger.homework_5.entitiy.GenreEntity;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<GenreEntity> findAllByGenreLike(String genre);

    List<GenreEntity> findAll();

    Optional<GenreEntity> findOne(Long id);

    Long saveAndReturnId(GenreEntity genre);

    void delete(Long id);

    void update(GenreEntity genre);
}
