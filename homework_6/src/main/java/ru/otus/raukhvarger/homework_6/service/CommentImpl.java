package ru.otus.raukhvarger.homework_6.service;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_6.dto.CommentDTO;
import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;
import ru.otus.raukhvarger.homework_6.jpa.repository.CommentRepository;
import ru.otus.raukhvarger.homework_6.utils.EntityConverter;

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
    public CommentDTO getById(Long id) {
        CommentEntity commentEntity = commentRepository.getById(id);
        return commentEntity != null ? EntityConverter.buildDTO(commentEntity) : null;
    }

    @Override
    public List<CommentDTO> getByBookId(Long bookId) {
        return commentRepository.getByBookId(bookId).stream()
                .map(EntityConverter::buildDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void update(CommentDTO comment) {
        commentRepository.update(comment.buildJpaEntity());
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDTO> getAll() {
        return commentRepository.getAll().stream()
                .map(EntityConverter::buildDTO)
                .collect(Collectors.toList());
    }
}
