package ru.otus.raukhvarger.homework_8.service.genre;

import ru.otus.raukhvarger.homework_8.dto.GenreDTO;

import java.util.List;

public interface GenreProvider {
    void create(GenreDTO genre);
    GenreDTO getById(String id);
    GenreDTO getByName(String name);
    GenreDTO getOrCreateByName(String name);
    void update(GenreDTO genre);
    void deleteById(String id);
    List<GenreDTO> getAll();
}
