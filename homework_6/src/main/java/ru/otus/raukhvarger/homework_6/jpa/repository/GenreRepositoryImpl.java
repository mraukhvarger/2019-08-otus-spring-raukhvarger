package ru.otus.raukhvarger.homework_6.jpa.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.raukhvarger.homework_6.jpa.entity.GenreEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public GenreEntity getById(Long genreId) {
        return em.find(GenreEntity.class, genreId);
    }

    @Override
    public GenreEntity getByName(String genreName) {
        try {
            TypedQuery<GenreEntity> query = em.createQuery(
                    "select g from GenreEntity g where g.genreName = :genreName",
                    GenreEntity.class
            );
            query.setParameter("genreName", genreName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void insert(GenreEntity genreEntity) {
        em.persist(genreEntity);
    }

    @Override
    public void update(GenreEntity genreEntity) {
        em.merge(genreEntity);
    }

    @Override
    public void deleteById(Long genreId) {
        em.remove(getById(genreId));
    }

    @Override
    public List<GenreEntity> getAll() {
        TypedQuery<GenreEntity> query = em.createQuery(
                "select g from GenreEntity g order by g.genreId",
                GenreEntity.class
        );
        return query.getResultList();
    }
}
