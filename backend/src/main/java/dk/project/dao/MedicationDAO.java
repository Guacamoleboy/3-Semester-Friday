package dk.project.dao;

import dk.project.entity.Medication;
import jakarta.persistence.EntityManager;

public class MedicationDAO extends EntityManagerDAO<Medication> {

    // Attributes

    // ________________________________________

    public MedicationDAO(EntityManager em){
        super(em, Medication.class);
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