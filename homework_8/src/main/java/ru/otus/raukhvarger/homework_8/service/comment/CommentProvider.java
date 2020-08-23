package ru.otus.raukhvarger.homework_8.service.comment;

import ru.otus.raukhvarger.homework_8.dto.CommentDTO;

import java.util.List;

public interface CommentProvider {
    void create(CommentDTO comment);
    CommentDTO getById(String id);
    List<CommentDTO> getByBookId(String bookId);
    void update(CommentDTO comment);
    void deleteById(String id);
    List<CommentDTO> getAll();
}
