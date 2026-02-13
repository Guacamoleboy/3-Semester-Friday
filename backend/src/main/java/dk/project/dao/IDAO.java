package dk.project.dao;

import java.util.List;

public interface IDAO <T> {

    // Attributes
    T create(T entity);
    T update(T entity);
    T getById(Object id);
    <R> R getColumnById(Object id, String column);
    List<T> getAll();
    T delete(T entity);
    T deleteById(Object id);
    void deleteAll();

}