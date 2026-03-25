package dk.project.dao.impl;

import dk.project.entity.Diagnose;
import jakarta.persistence.EntityManager;

public class DiagnoseDAO extends EntityManagerDAO<Diagnose> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public DiagnoseDAO(EntityManager em) {
        super(em, Diagnose.class);
    }

}