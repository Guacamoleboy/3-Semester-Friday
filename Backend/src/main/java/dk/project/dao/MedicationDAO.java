package dk.project.dao;

import dk.project.entity.Medication;
import jakarta.persistence.EntityManager;
import java.util.List;

public class MedicationDAO extends EntityManagerDAO {

    // Attributes

    public MedicationDAO(EntityManager em){
        super(em);
    }

    // ________________________________________

    public void createMedication(Medication medication){
        executeQuery(() -> em.persist(medication));
    }

    // ________________________________________

    public void updateMedication(Medication medication){
        executeQuery(() -> em.merge(medication));
    }

    // ________________________________________

    public void deleteMedication(int id){
        executeQuery(() -> {
            Medication medication = em.find(Medication.class, id);
            if (medication != null) em.remove(medication);
        });
    }

    // ________________________________________

    public int deleteAllMedications(){
        return executeQuery(() ->
            em.createQuery("DELETE FROM Medication x")
            .executeUpdate()
        );
    }

    // ________________________________________

    public Medication getMedicationById(int id){
        return executeQuery(() -> em.find(Medication.class, id));
    }

    // ________________________________________

    public String getNameById(int id){
        return executeQuery(() -> {
            String JPQL = "SELECT x.name FROM Medication x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
            .setParameter("id", id)
            .getSingleResult();
        });
    }

    // ________________________________________

    public String getDescriptionById(int id){
        return executeQuery(() -> {
            String JPQL = "SELECT x.description FROM Medication x WHERE x.id = :id";
            return em.createQuery(JPQL, String.class)
            .setParameter("id", id)
            .getSingleResult();
        });
    }

    // ________________________________________

    public List<Medication> getAllMedications(){
        return executeQuery(() -> {
            String JPQL = "SELECT x FROM Medication x";
            return em.createQuery(JPQL, Medication.class)
            .getResultList();
        });
    }

    // ________________________________________

    public boolean existsByName(String name){
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM Medication x WHERE x.name = :name", Long.class)
            .setParameter("name", name)
            .getSingleResult();
            return count > 0;
        });
    }

    // ________________________________________

    public Medication findByName(String name){
        return executeQuery(() -> em.createQuery(
    "SELECT x FROM Medication x WHERE x.name = :name", Medication.class)
        .setParameter("name", name)
        .getSingleResult()
        );
    }

}