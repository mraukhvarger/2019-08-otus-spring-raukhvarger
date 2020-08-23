package ru.otus.raukhvarger.homework_8.service.book;

import ru.otus.raukhvarger.homework_8.dto.BookDTO;

import java.util.List;

public interface BookProvider {
    void create(BookDTO book);
    BookDTO getById(String id);
    List<BookDTO> getByName(String name);
    void update(BookDTO book);
    void deleteById(String id);
    List<BookDTO> getAll();
}
