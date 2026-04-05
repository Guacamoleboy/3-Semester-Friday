package dk.project.controller.impl;

import dk.project.entity.DiagnoseClient;
import dk.project.service.internal.EntityManagerService;

public class DiagnoseClientController extends CRUDController<DiagnoseClient> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public DiagnoseClientController(EntityManagerService<DiagnoseClient> service) {
        super(service, DiagnoseClient.class, DiagnoseClientResponseMapper::toDTO);
    }

}
