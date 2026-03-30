package dk.project.service.internal;

import dk.project.dao.impl.MedicationCategoryDAO;
import dk.project.entity.MedicationCategory;
import jakarta.persistence.EntityManager;

public class MedicationCategoryService extends EntityManagerService<MedicationCategory> {

    // Attributes
    private final MedicationCategoryDAO medicationCategoryDAO;

    // _________________________________________________________________________________________________________________

    public MedicationCategoryService(EntityManager em) {
        super(new MedicationCategoryDAO(em), MedicationCategory.class);
        this.medicationCategoryDAO = (MedicationCategoryDAO) this.entityManagerDAO;
    }

}