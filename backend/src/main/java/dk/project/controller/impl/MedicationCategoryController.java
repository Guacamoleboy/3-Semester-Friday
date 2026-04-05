package dk.project.controller.impl;

import dk.project.entity.MedicationCategory;
import dk.project.service.internal.EntityManagerService;

public class MedicationCategoryController extends CRUDController<MedicationCategory> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public MedicationCategoryController(EntityManagerService<MedicationCategory> service) {
        super(service, MedicationCategory.class, MedicationCategoryResponseMapper::toDTO);
    }

}
