package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.jpa.entity.AuthorEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public AuthorEntity getById(Integer authorId) {
        return em.find(AuthorEntity.class, authorId);
    }

    @Override
    public AuthorEntity getByName(String authorName) {
        try {
            TypedQuery<AuthorEntity> query = em.createQuery(
                    "select a from AuthorEntity a where upper(a.authorName) = :authorName",
                    AuthorEntity.class
            );
            query.setParameter("authorName", authorName != null ? authorName.toUpperCase() : null);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insert(AuthorEntity authorEntity) {
        em.persist(authorEntity);
    }

    @Override
    public void update(AuthorEntity authorEntity) {
        em.merge(authorEntity);
    }

    @Override
    public void deleteById(Integer authorId) {
        em.remove(getById(authorId));
    }

    @Override
    public List<AuthorEntity> getAll() {
        TypedQuery<AuthorEntity> query = em.createQuery(
                "select a from AuthorEntity a order by a.authorId",
                AuthorEntity.class
        );
        return query.getResultList();
    }
}
