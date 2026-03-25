package dk.project.dao.impl;

import dk.project.entity.Baseline;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineDAO extends EntityManagerDAO<Baseline> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public BaselineDAO(EntityManager em) {
        super(em, Baseline.class);
    }

    // _________________________________________________________________________________________________________________

    public List<Baseline> findByClientId(String clientId) {
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Baseline x WHERE x.client.id = :clientId";
            return em.createQuery(JPQL, Baseline.class)
            .setParameter("clientId", clientId)
            .getResultList();
        });
    }

    // _________________________________________________________________________________________________________________

    public List<Baseline> findByDiagnoseId(int diagnoseId) {
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Baseline x WHERE x.diagnose.id = :diagnoseId";
            return em.createQuery(JPQL, Baseline.class)
            .setParameter("diagnoseId", diagnoseId)
            .getResultList();
        });
    }

}