package ru.otus.raukhvarger.homework_7.service;

import ru.otus.raukhvarger.homework_7.dto.CommentDTO;

import java.util.List;

public interface CommentProvider {
    void create(CommentDTO comment);

    CommentDTO getById(Integer id);

    List<CommentDTO> getByBookId(Integer bookId);

    void update(CommentDTO comment);

    void deleteById(Integer id);

    List<CommentDTO> getAll();
}