package ru.otus.raukhvarger.homework_5.dao;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.otus.raukhvarger.homework_5.annotation.Field;
import ru.otus.raukhvarger.homework_5.annotation.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class CommonRepository<T> implements ICommonRepository<T> {

    protected final Class type;

    protected final JdbcTemplate jdbcTemplate;

    public CommonRepository(Class type, JdbcTemplate jdbcTemplate) {
        this.type = type;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<T> findOne(Integer id) {
        try {
            return jdbcTemplate.queryForObject("select * from $tablename where id = ?".replace("$tablename", getTableName()),
                    new Object[]{id},
                    (r, i) -> Optional.of(resultToObject(r)));
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<T> findAll() {
        return jdbcTemplate.query("select * from $tablename".replace("$tablename", getTableName()),
                (r, i) -> resultToObject(r));
    }

    @Override
    public Integer saveAndReturnId(T t) {
        return new SimpleJdbcInsert(jdbcTemplate.getDataSource())
                .withTableName(getTableName())
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(getValuesAndColumn(t))
                .intValue();
    }

    @Override
    public void save(T t) {
        jdbcTemplate.update("insert into $tablename ($fields) values ($values)"
                .replace("$tablename", getTableName())
                .replace("$fields", getFieldsString(t))
                .replace("$values", getValuesTemplateString(t)),
                getValues(t, (fn1, fn2) -> 1));
    }

    @Override
    public void update(T t) {
        jdbcTemplate.update("update $tablename set $values where id = ?"
                .replace("$tablename", getTableName())
                .replace("$values", getValuesForUpdateTemplateString(t)),
                getValues(t, (fn1, fn2) -> fn1.column.equals("id") ? 0 : -1)
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update("delete $tablename where id = ?"
                .replace("$tablename", getTableName()),
                id);
    }

    private Map getValuesAndColumn(T t) {
        return getFieldsAndColumn(t.getClass()).stream()
                .map(fn -> {
                    try {
                        fn.field.setAccessible(true);
                        return Collections.singletonMap(fn.column, fn.field.get(t));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .reduce(new HashMap(), (acc, val) -> {
                    acc.putAll(val);
                    return acc;
                });
    }

    private String getValuesForUpdateTemplateString(T t) {
        return getFieldsAndColumn(t.getClass()).stream()
                .sorted((fn1, fn2) -> fn1.column.equals("id") ? 0 : -1)
                .filter(fn -> !fn.column.equals("id"))
                .map(fn -> fn.column + " = ?").collect(Collectors.joining(","));
    }

    private Object[] getValues(T t, Comparator<FieldAndColumn> s) {
        return getFieldsAndColumn(t.getClass()).stream().sorted(s).map(fn -> {
            try {
                fn.field.setAccessible(true);
                return fn.field.get(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList()).toArray();
    }

    private String getValuesTemplateString(T t) {
        return getFieldsAndColumn(t.getClass()).stream().map(fn -> "?").collect(Collectors.joining(","));
    }

    private String getFieldsString(T t) {
        return getFieldsAndColumn(t.getClass()).stream().map(fn -> fn.column).collect(Collectors.joining(","));
    }

    protected String getTableName() {
        return ((Table) type.getAnnotation(Table.class)).name();
    }

    private List<FieldAndColumn> getFieldsAndColumn(Class c) {
        return Arrays.stream(c.getDeclaredFields())
                .filter(f -> f.getAnnotation(Field.class) != null)
                .map(f -> new FieldAndColumn(f, f.getAnnotation(Field.class).name()))
                .collect(Collectors.toList());
    }

    @AllArgsConstructor
    private static class FieldAndColumn {
        java.lang.reflect.Field field;
        String column;
    }

    private T resultToObject(ResultSet r) {
        try {
            T t = (T) type.newInstance();

            getFieldsAndColumn(t.getClass()).forEach(fn -> {
                try {
                    fn.field.setAccessible(true);
                    fn.field.set(t, r.getObject(fn.column));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
