package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.jpa.entity.BookEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BookEntity getById(Long bookId) {
        return em.find(BookEntity.class, bookId);
    }

    @Override
    public List<BookEntity> getByName(String bookName) {
        try {
            TypedQuery<BookEntity> query = em.createQuery(
                    "select b from BookEntity b where b.bookName = :bookName",
                    BookEntity.class
            );
            query.setParameter("bookName", bookName);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insert(BookEntity bookEntity) {
        em.persist(bookEntity);
    }

    @Override
    public void update(BookEntity bookEntity) {
        em.merge(bookEntity);
    }

    @Override
    public void deleteById(Long bookId) {
        em.remove(getById(bookId));
    }

    @Override
    public List<BookEntity> getAll() {
        TypedQuery<BookEntity> query = em.createQuery("select b from BookEntity b order by b.bookId", BookEntity.class);
        return query.getResultList();
    }
}
