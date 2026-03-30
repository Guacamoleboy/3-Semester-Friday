package dk.project.dao.impl;

import dk.project.entity.MedicationCategory;
import jakarta.persistence.EntityManager;

public class MedicationCategoryDAO extends EntityManagerDAO<MedicationCategory> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public MedicationCategoryDAO(EntityManager em){
        super(em, MedicationCategory.class);
    }

}