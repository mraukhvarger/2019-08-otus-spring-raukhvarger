package ru.otus.raukhvarger.homework_6.service;

import ru.otus.raukhvarger.homework_6.dto.CommentDTO;

import java.util.List;

public interface CommentProvider {
    void create(CommentDTO comment);

    CommentDTO getById(Long id);

    List<CommentDTO> getByBookId(Long bookId);

    void update(CommentDTO comment);

    void deleteById(Long id);

    List<CommentDTO> getAll();
}
