package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.jpa.entity.CommentEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public CommentEntity getById(Long commentId) {
        return em.find(CommentEntity.class, commentId);
    }

    @Override
    public List<CommentEntity> getByBookId(Long bookId) {
        try {
            TypedQuery<CommentEntity> query = em.createQuery(
                    "select c from CommentEntity c where c.bookId = :bookId order by c.commentId",
                    CommentEntity.class
            );
            query.setParameter("bookId", bookId);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insert(CommentEntity commentEntity) {
        em.persist(commentEntity);
    }

    @Override
    public void update(CommentEntity commentEntity) {
        em.merge(commentEntity);
    }

    @Override
    public void deleteById(Long commentId) {
        em.remove(getById(commentId));
    }

    @Override
    public List<CommentEntity> getAll() {
        TypedQuery<CommentEntity> query = em.createQuery("select c from CommentEntity c order by c.commentId", CommentEntity.class);
        return query.getResultList();
    }
}
