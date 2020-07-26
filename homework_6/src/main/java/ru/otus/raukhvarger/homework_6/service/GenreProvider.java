package ru.otus.raukhvarger.homework_6.service;

import ru.otus.raukhvarger.homework_6.dto.GenreDTO;

import java.util.List;

public interface GenreProvider {
    void create(GenreDTO genre);

    GenreDTO getById(Integer id);

    GenreDTO getByName(String name);

    GenreDTO getOrCreateByName(String name);

    void update(GenreDTO genre);

    void deleteById(Integer id);

    List<GenreDTO> getAll();
}
