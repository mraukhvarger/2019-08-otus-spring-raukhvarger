package ru.otus.raukhvarger.homework_6.jpa.repository;

import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;

import java.util.List;

public interface CommentRepository {
    CommentEntity getById(Integer bookId);

    List<CommentEntity> getByBookId(Integer bookId);

    void insert(CommentEntity commentEntity);

    void update(CommentEntity commentEntity);

    void deleteById(Integer commentId);

    List<CommentEntity> getAll();
}
