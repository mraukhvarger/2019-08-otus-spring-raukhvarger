package ru.otus.raukhvarger.homework_8.service.author;

import ru.otus.raukhvarger.homework_8.dto.AuthorDTO;

import java.util.List;

public interface AuthorProvider {
    void create(AuthorDTO author);
    AuthorDTO getById(String id);
    AuthorDTO getByName(String name);
    AuthorDTO getOrCreateByName(String name);
    void update(AuthorDTO author);
    void deleteById(String id);
    List<AuthorDTO> getAll();
    AuthorDTO getByNameWithBooks(String name);
}
