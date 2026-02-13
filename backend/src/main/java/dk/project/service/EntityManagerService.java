package dk.project.service;

import dk.project.dao.EntityManagerDAO;
import java.util.List;

public class EntityManagerService<T> {

    // Attributes
    protected final EntityManagerDAO<T> entityManagerDAO;
    protected final Class<T> entityClass;

    // _____________________________________________________

    public EntityManagerService(EntityManagerDAO<T> entityManagerDAO, Class<T> entityClass){
        this.entityManagerDAO = entityManagerDAO;
        this.entityClass = entityClass;
    }

    // _________________________________________________

    public T create(T entity){
        validateNotEmpty(entity, entityClass.getSimpleName() + ".entity");
        return entityManagerDAO.create(entity);
    }

    // _________________________________________________

    public T update(T entity){
        validateNotEmpty(entity, entityClass.getSimpleName() + ".entity");
        return entityManagerDAO.update(entity);
    }

    // _________________________________________________

    public void deleteById(Object id){
        validateNotEmpty(id, entityClass.getSimpleName() + ".id");
        entityManagerDAO.deleteById(id);
    }

    // _________________________________________________

    public void deleteAll(){
        entityManagerDAO.deleteAll();
    }

    // _________________________________________________

    public T getById(Object id){
        validateNotEmpty(id, entityClass.getSimpleName() + ".id");
        return entityManagerDAO.getById(id);
    }

    // _________________________________________________

    public List<T> getAll(){
        return entityManagerDAO.getAll();
    }

    // _________________________________________________

    public <R> R getColumnById(Object id, String column){
        validateNotEmpty(id, entityClass.getSimpleName() + ".id");
        validateNotEmpty(column, entityClass.getSimpleName() + "." + column);
        return entityManagerDAO.getColumnById(id, column);
    }

    // _________________________________________________

    protected void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " må ikke være null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " kan ikke være tom");
        }
    }

}