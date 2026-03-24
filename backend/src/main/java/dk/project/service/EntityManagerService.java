package dk.project.service;

import dk.project.dao.impl.EntityManagerDAO;
import java.util.List;

public class EntityManagerService <T> {

    // Attributes
    protected final EntityManagerDAO<T> entityManagerDAO;
    protected final Class<T> classSpecific;

    // _____________________________________________________

    public EntityManagerService(EntityManagerDAO<T> entityManagerDAO, Class<T> entityClass){
        this.entityManagerDAO = entityManagerDAO;
        this.classSpecific = entityClass;
    }

    // _________________________________________________

    public T create(T entity){
        validateNotEmpty(entity, classSpecific.getSimpleName() + ".entity");
        return entityManagerDAO.create(entity);
    }

    // _________________________________________________

    public T update(T entity){
        validateNotEmpty(entity, classSpecific.getSimpleName() + ".entity");
        return entityManagerDAO.update(entity);
    }

    // _________________________________________________

    public T delete(T entity){
        validateNotEmpty(entity, classSpecific.getSimpleName() + ".entity");
        return entityManagerDAO.delete(entity);
    }

    // _________________________________________________

    public T deleteById(Object id){
        validateNotEmpty(id, classSpecific.getSimpleName() + ".id");
        return entityManagerDAO.deleteById(id);
    }

    // _________________________________________________

    public int deleteAll(){
        return entityManagerDAO.deleteAll();
    }

    // _________________________________________________

    public int deleteAllSafe() {
        return entityManagerDAO.deleteAllSafe();
    }

    // _________________________________________________

    public T getById(Object id){
        validateNotEmpty(id, classSpecific.getSimpleName() + ".id");
        return entityManagerDAO.getById(id);
    }

    // _________________________________________________

    public List<T> getAll(){
        return entityManagerDAO.getAll();
    }

    // _________________________________________________

    public boolean existByColumn(Object value, String column) {
        validateNotEmpty(value, classSpecific.getSimpleName() + ".value");
        validateNotEmpty(column, classSpecific.getSimpleName() + "." + column);
        return entityManagerDAO.existByColumn(value, column);
    }

    // _________________________________________________

    public <R> R getColumnById(Object id, String column){
        validateNotEmpty(id, classSpecific.getSimpleName() + ".id");
        validateNotEmpty(column, classSpecific.getSimpleName() + "." + column);
        return entityManagerDAO.getColumnById(id, column);
    }

    // _________________________________________________

    public int updateColumnById(Object id, String column, Object value){
        validateNotEmpty(id, classSpecific.getSimpleName() + ".id");
        validateNotEmpty(column, classSpecific.getSimpleName() + "." + column);
        return entityManagerDAO.updateColumnById(id, column, value);
    }

    // _________________________________________________

    public T findEntityByColumn(Object value, String column) {
        validateNotEmpty(value, "value");
        validateNotEmpty(column, classSpecific.getSimpleName() + "." + column);
        return entityManagerDAO.findEntityByColumn(value, column);
    }

    // _________________________________________________

    protected void validateNotEmpty(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " can't be null");
        }
        if (value instanceof String text && text.isBlank()) {
            throw new IllegalArgumentException(fieldName + " can't be empty");
        }
    }

}