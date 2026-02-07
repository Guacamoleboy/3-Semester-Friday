package dk.project.dao;

import dk.project.entity.BaselineIndividual;
import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineIndividualDAO extends EntityManagerDAO {

    // ________________________________________

    public BaselineIndividualDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createBaselineIndividual(BaselineIndividual bi){
        executeQuery(() -> em.persist(bi));
    }

    // ________________________________________

    public void updateBaselineIndividual(BaselineIndividual bi){
        executeQuery(() -> em.merge(bi));
    }

    // ________________________________________

    public void deleteBaselineIndividual(int id){
        executeQuery(() -> {
            BaselineIndividual baselineIndividual = em.find(BaselineIndividual.class, id);
            if (baselineIndividual != null) em.remove(baselineIndividual);
        });
    }

    // ________________________________________

    public int deleteAllBaselineIndividuals(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM BaselineIndividual x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public BaselineIndividual getBaselineIndividualById(int id){
        return executeQuery(() -> em.find(BaselineIndividual.class, id));
    }

    // ________________________________________

    public List<BaselineIndividual> getAllBaselineIndividuals(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM BaselineIndividual x";
            return em.createQuery(JPQL, BaselineIndividual.class)
            .getResultList();
        });
    }

    // ________________________________________

    public void addSideEffect(BaselineIndividual baselineIndividual, SideEffect sideEffect){
        baselineIndividual.getSideEffects().add(sideEffect);
        updateBaselineIndividual(baselineIndividual);
    }

    // ________________________________________

    public void removeSideEffect(BaselineIndividual baselineIndividual, SideEffect sideEffect){
        baselineIndividual.getSideEffects().remove(sideEffect);
        updateBaselineIndividual(baselineIndividual);
    }

    // ________________________________________

    public List<BaselineIndividual> findByBaselineId(int baselineId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM BaselineIndividual x WHERE x.baseline.id = :baselineId";
            return em.createQuery(JPQL, BaselineIndividual.class)
            .setParameter("baselineId", baselineId)
            .getResultList();
        });
    }

}