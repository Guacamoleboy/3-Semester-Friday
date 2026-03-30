package dk.project.dao.impl;

import dk.project.entity.SideEffectMedication;
import jakarta.persistence.EntityManager;

public class SideEffectMedicationDAO extends EntityManagerDAO<SideEffectMedication> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public SideEffectMedicationDAO(EntityManager em) {
        super(em, SideEffectMedication.class);
    }

}