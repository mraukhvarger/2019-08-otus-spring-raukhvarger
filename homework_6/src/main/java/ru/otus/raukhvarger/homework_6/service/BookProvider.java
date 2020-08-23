package ru.otus.raukhvarger.homework_6.service;

import ru.otus.raukhvarger.homework_6.dto.BookDTO;

import java.util.List;

public interface BookProvider {
    void create(BookDTO book);

    BookDTO getById(Long id);

    List<BookDTO> getByName(String name);

    void update(BookDTO book);

    void deleteById(Long id);

    List<BookDTO> getAll();
}
