package ru.otus.raukhvarger.homework_6.service;

import ru.otus.raukhvarger.homework_6.dto.AuthorDTO;

import java.util.List;

public interface AuthorProvider {
    void create(AuthorDTO author);

    AuthorDTO getById(Integer id);

    AuthorDTO getByName(String name);

    AuthorDTO getOrCreateByName(String name);

    void update(AuthorDTO author);

    void deleteById(Integer id);

    List<AuthorDTO> getAll();

    AuthorDTO getByNameWithBooks(String name);
}
