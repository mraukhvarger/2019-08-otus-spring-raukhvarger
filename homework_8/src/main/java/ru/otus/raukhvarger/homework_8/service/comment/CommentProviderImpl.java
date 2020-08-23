package ru.otus.raukhvarger.homework_8.service.comment;

import org.springframework.stereotype.Service;
import ru.otus.raukhvarger.homework_8.db.entity.Book;
import ru.otus.raukhvarger.homework_8.db.entity.Comment;
import ru.otus.raukhvarger.homework_8.db.repository.BookRepository;
import ru.otus.raukhvarger.homework_8.db.repository.CommentRepository;
import ru.otus.raukhvarger.homework_8.dto.CommentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentProviderImpl implements CommentProvider {
    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;

    public CommentProviderImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void create(CommentDTO comment) {
        commentRepository.save(comment.buildDBEntity());
    }

    @Override
    public CommentDTO getById(String id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        return comment != null ? comment.buildDTO() : null;
    }

    @Override
    public List<CommentDTO> getByBookId(String bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if (book != null && book.getComments() != null) {
            return book.getComments().stream()
                    .map(g -> g.buildDTO())
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public void update(CommentDTO comment) {
        commentRepository.save(comment.buildDBEntity());
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
        //bookRepository.deleteCommentFromBookById(id);
    }

    @Override
    public List<CommentDTO> getAll() {
        return commentRepository.findAll().stream()
                .map(g -> g.buildDTO())
                .collect(Collectors.toList());
    }
}
