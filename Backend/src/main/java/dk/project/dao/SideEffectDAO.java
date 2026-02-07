package dk.project.dao;

import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;
import java.util.List;

public class SideEffectDAO extends EntityManagerDAO {

    // Attributes

    public SideEffectDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createSideEffect(SideEffect sideEffect){
        executeQuery(() -> em.persist(sideEffect));
    }

    // ________________________________________

    public void updateSideEffect(SideEffect sideEffect){
        executeQuery(() -> em.merge(sideEffect));
    }

    // ________________________________________

    public void deleteSideEffect(int id){
        executeQuery(() -> {
            SideEffect sideEffect = em.find(SideEffect.class, id);
            if (sideEffect != null) em.remove(sideEffect);
        });
    }

    // ________________________________________

    public int deleteAllSideEffects(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM SideEffect x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public SideEffect getSideEffectById(int id){
        return executeQuery(() -> em.find(SideEffect.class, id));
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

    // ________________________________________

    public List<SideEffect> getAllSideEffects(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM SideEffect x";
            return em.createQuery(JPQL, SideEffect.class)
            .getResultList();
        });
    }

}