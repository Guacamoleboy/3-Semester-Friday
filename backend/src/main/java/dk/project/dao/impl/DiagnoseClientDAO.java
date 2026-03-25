package dk.project.dao.impl;

import dk.project.entity.DiagnoseClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseClientDAO extends EntityManagerDAO<DiagnoseClient> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public DiagnoseClientDAO(EntityManager em) {
        super(em, DiagnoseClient.class);
    }

    // _________________________________________________________________________________________________________________

    public List<DiagnoseClient> findByClientId(String clientId) {
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM DiagnoseClient x WHERE x.client.id = :clientId";
            return em.createQuery(JPQL, DiagnoseClient.class)
                    .setParameter("clientId", clientId)
                    .getResultList();
        });
    }

    // _________________________________________________________________________________________________________________

    public List<DiagnoseClient> findByDiagnoseId(int diagnoseId) {
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM DiagnoseClient x WHERE x.diagnose.id = :diagnoseId";
            return em.createQuery(JPQL, DiagnoseClient.class)
                    .setParameter("diagnoseId", diagnoseId)
                    .getResultList();
        });
    }

}