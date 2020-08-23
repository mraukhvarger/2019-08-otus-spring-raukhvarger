package ru.otus.raukhvarger.homework_8.db.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.raukhvarger.homework_8.db.entity.Book;
import ru.otus.raukhvarger.homework_8.db.entity.Comment;
import ru.otus.raukhvarger.homework_8.db.repository.BookRepository;
import ru.otus.raukhvarger.homework_8.db.repository.CommentRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test comment repository")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.raukhvarger.homework_8"})
public class CommentRepositoryTest {
    public static final String TEST_NEW_COMMENT_TEXT = "test comment";
    public static final String TEST_EXISTING_COMMENT_ID = "879e650f-e92b-40f8-a936-fa633687c8c9";
    public static final String TEST_EXISTING_COMMENT_TEST = "Test comment 1";
    public static final String TEST_NEW_COMMENT_ID = "046655eb-d84d-424c-8e6d-05b47041d30b";
    public static final String TEST_EXISTING_BOOK_NAME = "Fahrenheit 451";
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Test
    @DisplayName("test create comment")
    void testCreateComment() {
        Book book = bookRepository.findByBookName(TEST_EXISTING_BOOK_NAME).get(0);
        Comment comment = Comment.builder()
                .commentId(TEST_NEW_COMMENT_ID)
                .comment(TEST_NEW_COMMENT_TEXT)
                .build();
        commentRepository.save(comment);
        book.getComments().add(comment);
        bookRepository.save(book);
        Comment createdComment = commentRepository.findById(TEST_NEW_COMMENT_ID).orElse(Comment.builder().build());
        assertEquals(createdComment.getComment(), TEST_NEW_COMMENT_TEXT);
    }

    @Test
    @DisplayName("test get comment by id")
    void testGetCommentById() {
        Comment comment = commentRepository.findById(TEST_EXISTING_COMMENT_ID).orElse(Comment.builder().build());
        assertEquals(comment.getComment(), TEST_EXISTING_COMMENT_TEST);
    }

    @Test
    @DisplayName("test get comment by book bookId")
    void testGetCommentByBookId() {
        List<Comment> comments = bookRepository.findByBookName(TEST_EXISTING_BOOK_NAME).get(0).getComments();
        assertNotNull(comments);
        assertEquals(comments.size(), 1);
    }
}
