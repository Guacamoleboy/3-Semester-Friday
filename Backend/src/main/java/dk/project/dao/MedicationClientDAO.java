package dk.project.dao;

import dk.project.entity.MedicationClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MedicationClientDAO extends EntityManagerDAO {

    // ________________________________________

    public MedicationClientDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createMedicationClient(MedicationClient medicationClient){
        executeQuery(() -> em.persist(medicationClient));
    }

    // ________________________________________

    public void updateMedicationClient(MedicationClient medicationClient){
        executeQuery(() -> em.merge(medicationClient));
    }

    // ________________________________________

    public void deleteMedicationClient(int id){
        executeQuery(() -> {
            MedicationClient medicationClient = em.find(MedicationClient.class, id);
            if (medicationClient != null) em.remove(medicationClient);
        });
    }

    // ________________________________________

    public int deleteAllMedicationClients(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM MedicationClient x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public MedicationClient getMedicationClientById(int id){
        return executeQuery(() -> em.find(MedicationClient.class, id));
    }

    // ________________________________________

    public List<MedicationClient> getAllMedicationClients(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM MedicationClient x";
            return em.createQuery(JPQL, MedicationClient.class)
            .getResultList();
        });
    }

    // ________________________________________

    public List<MedicationClient> findByClientId(String clientId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM MedicationClient x WHERE x.client.id = :clientId";
            return em.createQuery(JPQL, MedicationClient.class)
            .setParameter("clientId", clientId)
            .getResultList();
        });
    }

    // ________________________________________

    public List<MedicationClient> findByMedicationId(int medicationId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM MedicationClient x WHERE x.medication.id = :medicationId";
            return em.createQuery(JPQL, MedicationClient.class)
            .setParameter("medicationId", medicationId)
            .getResultList();
        });
    }

}