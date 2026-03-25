package dk.project.dao.impl;

import dk.project.entity.MedicationClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MedicationClientDAO extends EntityManagerDAO<MedicationClient> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public MedicationClientDAO(EntityManager em){
        super(em, MedicationClient.class);
    }

    // _________________________________________________________________________________________________________________

    public List<MedicationClient> findByClientId(String clientId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM MedicationClient x WHERE x.client.id = :clientId";
            return em.createQuery(JPQL, MedicationClient.class)
            .setParameter("clientId", clientId)
            .getResultList();
        });
    }

    // _________________________________________________________________________________________________________________

    public List<MedicationClient> findByMedicationId(int medicationId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM MedicationClient x WHERE x.medication.id = :medicationId";
            return em.createQuery(JPQL, MedicationClient.class)
            .setParameter("medicationId", medicationId)
            .getResultList();
        });
    }

}