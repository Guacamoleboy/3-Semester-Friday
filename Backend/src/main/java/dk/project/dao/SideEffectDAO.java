package dk.project.dao;

import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;

public class SideEffectDAO extends EntityManagerDAO<SideEffect> {

    // Attributes

    // ________________________________________

    public SideEffectDAO(EntityManager em){
        super(em, SideEffect.class);
    }

    // ________________________________________

    public String getNoteById(int id){
        return executeQuery(() -> {
            String JPQL = "SELECT x.note FROM SideEffect x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
                    .setParameter("id", id)
                    .getSingleResult();
        });
    }

}