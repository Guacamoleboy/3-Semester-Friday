package dk.project.dao;

import dk.project.entity.Diagnose;
import jakarta.persistence.EntityManager;

public class DiagnoseDAO extends EntityManagerDAO<Diagnose> {

    // Attributes

    // ________________________________________

    public DiagnoseDAO(EntityManager em) {
        super(em, Diagnose.class);
    }

}