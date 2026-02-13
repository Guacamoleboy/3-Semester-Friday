package dk.project.dao;

import dk.project.entity.Diagnose;
import jakarta.persistence.EntityManager;

public class DiagnoseDAO extends EntityManagerDAO<Diagnose> {

    // Attributes

    // ________________________________________

    public DiagnoseDAO(EntityManager em) {
        super(em, Diagnose.class);
    }

    // ________________________________________

    public boolean existsByName(String name) {
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM Diagnose x WHERE x.name = :name", Long.class)
            .setParameter("name", name)
            .getSingleResult();
            return count > 0;
        });
    }

    // ________________________________________

    public Diagnose findByName(String name) {
        return executeQuery(() -> em.createQuery(
        "SELECT x FROM Diagnose x WHERE x.name = :name", Diagnose.class)
            .setParameter("name", name)
            .getSingleResult()
        );
    }

}