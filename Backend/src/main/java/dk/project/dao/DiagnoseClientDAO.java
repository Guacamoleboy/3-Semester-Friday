package dk.project.dao;

import dk.project.entity.DiagnoseClient;
import jakarta.persistence.EntityManager;
import java.util.List;

public class DiagnoseClientDAO extends EntityManagerDAO {

    // Attributes

    public DiagnoseClientDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createDiagnoseClient(DiagnoseClient diagnoseClient){
        executeQuery(() -> em.persist(diagnoseClient));
    }

    // ________________________________________

    public void updateDiagnoseClient(DiagnoseClient diagnoseClient){
        executeQuery(() -> em.merge(diagnoseClient));
    }

    // ________________________________________

    public void deleteDiagnoseClient(int id){
        executeQuery(() -> {
            DiagnoseClient diagnoseClient = em.find(DiagnoseClient.class, id);
            if (diagnoseClient != null) em.remove(diagnoseClient);
        });
    }

    // ________________________________________

    public int deleteAllDiagnoseClients(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM DiagnoseClient x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public DiagnoseClient getDiagnoseClientById(int id){
        return executeQuery(() -> em.find(DiagnoseClient.class, id));
    }

    // ________________________________________

    public List<DiagnoseClient> getAllDiagnoseClients(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM DiagnoseClient x";
            return em.createQuery(JPQL, DiagnoseClient.class)
            .getResultList();
        });
    }

    // ________________________________________

    public List<DiagnoseClient> findByClientId(String clientId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM DiagnoseClient x WHERE x.client.id = :clientId";
            return em.createQuery(JPQL, DiagnoseClient.class)
            .setParameter("clientId", clientId)
            .getResultList();
        });
    }

    // ________________________________________

    public List<DiagnoseClient> findByDiagnoseId(int diagnoseId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM DiagnoseClient x WHERE x.diagnose.id = :diagnoseId";
            return em.createQuery(JPQL, DiagnoseClient.class)
            .setParameter("diagnoseId", diagnoseId)
            .getResultList();
        });
    }

}
