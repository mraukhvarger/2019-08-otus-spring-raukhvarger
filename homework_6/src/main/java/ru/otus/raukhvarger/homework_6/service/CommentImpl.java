package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_6.dto.CommentDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentImpl implements CommentProvider {
    private final CommentRepository commentRepository;

    public CommentImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void create(CommentDTO comment) {
        commentRepository.insert(comment.buildJpaEntity());
    }

    @Override
    public CommentDTO getById(Integer id) {
        CommentEntity commentEntity = commentRepository.getById(id);
        return commentEntity != null ? commentEntity.buildDTO() : null;
    }

    @Override
    public List<CommentDTO> getByBookId(Integer bookId) {
        return commentRepository.getByBookId(bookId).stream()
                .map(g -> g.buildDTO())
                .collect(Collectors.toList());
    }

    @Override
    public void update(CommentDTO comment) {
        commentRepository.update(comment.buildJpaEntity());
    }

    @Override
    public void deleteById(Integer id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDTO> getAll() {
        return commentRepository.getAll().stream()
                .map(g -> g.buildDTO())
                .collect(Collectors.toList());
    }
}
