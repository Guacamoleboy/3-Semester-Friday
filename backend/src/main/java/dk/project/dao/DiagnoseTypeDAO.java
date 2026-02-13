package dk.project.dao;

import dk.project.entity.DiagnoseType;
import jakarta.persistence.EntityManager;

public class DiagnoseTypeDAO extends EntityManagerDAO<DiagnoseType> {

    // Attributes

    // ________________________________________

    public DiagnoseTypeDAO(EntityManager em) {
        super(em, DiagnoseType.class);
    }

    // ________________________________________

    public boolean existsByName(String name){
        return executeQuery(() -> {
            Long count = em.createQuery(
        "SELECT COUNT(x) FROM DiagnoseType x WHERE x.name = :name", Long.class)
            .setParameter("name", name)
            .getSingleResult();
            return count > 0;
        });
    }

    // ________________________________________

    public DiagnoseType findByName(String name){
        return executeQuery(() -> em.createQuery(
        "SELECT x FROM DiagnoseType x WHERE x.name = :name", DiagnoseType.class)
            .setParameter("name", name)
            .getSingleResult()
        );
    }

}