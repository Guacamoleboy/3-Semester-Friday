package dk.project.controller.impl;

import dk.project.entity.Diagnose;
import dk.project.service.internal.EntityManagerService;

public class DiagnoseController extends CRUDController<Diagnose> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public DiagnoseController(EntityManagerService<Diagnose> service) {
        super(service, Diagnose.class, DiagnoseResponseMapper::toDTO);
    }

}
