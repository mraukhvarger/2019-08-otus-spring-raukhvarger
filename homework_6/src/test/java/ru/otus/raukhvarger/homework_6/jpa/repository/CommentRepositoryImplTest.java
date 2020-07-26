package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;
import ru.otus.raukhvarger.homework_6.spring.Application;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Test comment repository")
@SpringBootTest(classes = Application.class)
public class CommentRepositoryImplTest {
    public static final String TEST_NEW_COMMENT_TEXT = "test comment";
    public static final Integer TEST_EXISTING_COMMENT_ID = 1;
    public static final String TEST_EXISTING_COMMENT_TEST = "451 градус по фаренгейту, Рэй Брэдбери";
    public static final Integer TEST_NEW_COMMENT_ID = 7;
    public static final Integer TEST_EXISTING_BOOK_ID = 1;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("test create comment")
    void testCreateComment() {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setBookId(TEST_EXISTING_BOOK_ID);
        commentEntity.setComment(TEST_NEW_COMMENT_TEXT);
        commentRepository.insert(commentEntity);
        CommentEntity createdCommentEntity = commentRepository.getById(TEST_NEW_COMMENT_ID);
        assertEquals(createdCommentEntity.getComment(), TEST_NEW_COMMENT_TEXT);
    }

    @Test
    @DisplayName("test get comment by id")
    void testGetCommentById() {
        CommentEntity commentEntity = commentRepository.getById(TEST_EXISTING_COMMENT_ID);
        assertEquals(commentEntity.getComment(), TEST_EXISTING_COMMENT_TEST);
    }

    @Test
    @DisplayName("test get comment by book id")
    void testGetCommentByBookId() {
        List<CommentEntity> commentEntities = commentRepository.getByBookId(TEST_EXISTING_BOOK_ID);
        assertNotNull(commentEntities);
        assertEquals(commentEntities.size(), 1);
    }
}
