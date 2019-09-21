package ru.otus.raukhvarger.homework_5.dao;

import java.util.List;
import java.util.Optional;

public interface ICommonRepository<T> {

    Optional<T> findOne(Integer id);

    List<T> findAll();

    Integer saveAndReturnId(T t);

    void save(T t);

    void update(T t);

    void delete(Integer id);

}
