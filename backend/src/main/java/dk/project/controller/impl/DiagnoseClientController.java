package dk.project.controller.impl;

import dk.project.entity.DiagnoseClient;
import dk.project.service.internal.EntityManagerService;
import dk.project.mapper.response.DiagnoseClientResponseMapper;

public class DiagnoseClientController extends CRUDController<DiagnoseClient> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public DiagnoseClientController(EntityManagerService<DiagnoseClient> service) {
        super(service, DiagnoseClient.class, DiagnoseClientResponseMapper::toDTO);
    }

}
