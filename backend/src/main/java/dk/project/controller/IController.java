package dk.project.controller;

import java.util.List;

public interface IController <T> {

    T create(T entity);
    T update(T entity);
    T getById(Object id);
    List<T> getAll();
    T delete(T entity);
    T deleteById(Object id);
    int deleteAll();
    int deleteAllSafe();

}