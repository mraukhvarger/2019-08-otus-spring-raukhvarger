package ru.otus.raukhvarger.homework_7.service;

import ru.otus.raukhvarger.homework_7.dto.BookDTO;

import java.util.List;

public interface BookProvider {
    void create(BookDTO book);

    BookDTO getById(Integer id);

    List<BookDTO> getByName(String name);

    void update(BookDTO book);

    void deleteById(Integer id);

    List<BookDTO> getAll();
}
