package dk.project.dao;

import dk.project.entity.Baseline;
import jakarta.persistence.EntityManager;
import java.util.List;

public class BaselineDAO extends EntityManagerDAO {

    // ________________________________________

    public BaselineDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createBaseline(Baseline baseline){
        executeQuery(() -> em.persist(baseline));
    }

    // ________________________________________

    public void updateBaseline(Baseline baseline){
        executeQuery(() -> em.merge(baseline));
    }

    // ________________________________________

    public void deleteBaseline(int id){
        executeQuery(() -> {
            Baseline baseline = em.find(Baseline.class, id);
            if (baseline != null) em.remove(baseline);
        });
    }

    // ________________________________________

    public int deleteAllBaselines(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM Baseline x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public Baseline getBaselineById(int id){
        return executeQuery(() -> em.find(Baseline.class, id));
    }

    // ________________________________________

    public List<Baseline> getAllBaselines(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Baseline x";
            return em.createQuery(JPQL, Baseline.class)
            .getResultList();
        });
    }

    // ________________________________________

    public List<Baseline> findByClientId(String clientId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Baseline x WHERE x.client.id = :clientId";
            return em.createQuery(JPQL, Baseline.class)
            .setParameter("clientId", clientId)
            .getResultList();
        });
    }

    // ________________________________________

    public List<Baseline> findByDiagnoseId(int diagnoseId){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Baseline x WHERE x.diagnose.id = :diagnoseId";
            return em.createQuery(JPQL, Baseline.class)
            .setParameter("diagnoseId", diagnoseId)
            .getResultList();
        });
    }

}