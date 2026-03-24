package dk.project.controller.impl;

import dk.project.controller.IController;
import dk.project.service.EntityManagerService;
import java.util.List;

public class CRUDController <T> implements IController <T> {

    // Attributes
    protected final EntityManagerService<T> service;

    // ___________________________________________________________________________________________

    protected CRUDController(EntityManagerService<T> service) {
        this.service = service;
    }

    // ___________________________________________________________________________________________

    @Override
    public T create(T entity) {
        return service.create(entity);
    }

    // ___________________________________________________________________________________________

    @Override
    public T update(T entity) {
        return service.update(entity);
    }

    // ___________________________________________________________________________________________

    @Override
    public T getById(Object id) {
        return service.getById(id);
    }

    // ___________________________________________________________________________________________

    @Override
    public List<T> getAll() {
        return service.getAll();
    }

    // ___________________________________________________________________________________________

    @Override
    public T delete(T entity) {
        return service.delete(entity);
    }

    // ___________________________________________________________________________________________

    @Override
    public T deleteById(Object id) {
        service.deleteById(id);
        return null;
    }

    // ___________________________________________________________________________________________

    @Override
    public int deleteAll() {
        return service.deleteAll();
    }

    // ___________________________________________________________________________________________

    @Override
    public int deleteAllSafe() {
        return service.deleteAllSafe();
    }

}