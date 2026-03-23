package dk.project.dao.impl;

import dk.project.entity.BaselineIndividual;
import dk.project.entity.SideEffect;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineIndividualDAO extends EntityManagerDAO<BaselineIndividual> {

    // Attributes

    // ________________________________________

    public BaselineIndividualDAO(EntityManager em) {
        super(em, BaselineIndividual.class);
    }

    // ________________________________________

    public void addSideEffect(BaselineIndividual baselineIndividual, SideEffect sideEffect) {
        baselineIndividual.getSideEffects().add(sideEffect);
        update(baselineIndividual);
    }

    // ________________________________________

    public void removeSideEffect(BaselineIndividual baselineIndividual, SideEffect sideEffect) {
        baselineIndividual.getSideEffects().remove(sideEffect);
        update(baselineIndividual);
    }

    // ________________________________________

    public List<BaselineIndividual> findByBaselineId(int baselineId) {
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM BaselineIndividual x WHERE x.baseline.id = :baselineId";
            return em.createQuery(JPQL, BaselineIndividual.class)
            .setParameter("baselineId", baselineId)
            .getResultList();
        });
    }

}