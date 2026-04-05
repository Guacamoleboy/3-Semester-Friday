package dk.project.controller.impl;

import dk.project.entity.DiagnoseType;
import dk.project.mapper.response.DiagnoseTypeResponseMapper;
import dk.project.service.internal.EntityManagerService;

public class DiagnoseTypeController extends CRUDController<DiagnoseType> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public DiagnoseTypeController(EntityManagerService<DiagnoseType> service) {
        super(service, DiagnoseType.class, DiagnoseTypeResponseMapper::toDTO);
    }

}
