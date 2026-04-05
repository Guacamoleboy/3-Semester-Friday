package dk.project.controller.impl;

import dk.project.entity.Medication;
import dk.project.service.internal.EntityManagerService;

public class MedicationController extends CRUDController<Medication> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public MedicationController(EntityManagerService<Medication> service) {
        super(service, Medication.class, MedicationResponseMapper::toDTO);
    }

}
