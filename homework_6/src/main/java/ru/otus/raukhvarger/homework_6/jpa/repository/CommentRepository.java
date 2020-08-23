package ru.otus.raukhvarger.homework_6.jpa.repository;

import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;

import java.util.List;

public interface CommentRepository {
    CommentEntity getById(Long bookId);

    List<CommentEntity> getByBookId(Long bookId);

    void insert(CommentEntity commentEntity);

    void update(CommentEntity commentEntity);

    void deleteById(Long commentId);

    List<CommentEntity> getAll();
}
