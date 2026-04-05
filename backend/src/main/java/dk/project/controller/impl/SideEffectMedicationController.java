package dk.project.controller.impl;

import dk.project.entity.SideEffectMedication;
import dk.project.mapper.response.SideEffectMedicationResponseMapper;
import dk.project.service.internal.EntityManagerService;

public class SideEffectMedicationController extends CRUDController<SideEffectMedication> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public SideEffectMedicationController(EntityManagerService<SideEffectMedication> service) {
        super(service, SideEffectMedication.class, SideEffectMedicationResponseMapper::toDTO);
    }

}
