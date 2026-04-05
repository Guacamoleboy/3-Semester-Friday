package dk.project.controller.impl;

import dk.project.entity.MedicationClient;
import dk.project.service.internal.EntityManagerService;

public class MedicationClientController extends CRUDController<MedicationClient> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public MedicationClientController(EntityManagerService<MedicationClient> service) {
        super(service, MedicationClient.class, MedicationClientResponseMapper::toDTO);
    }

}
