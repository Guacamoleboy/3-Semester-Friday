package dk.project.controller.impl;

import dk.project.entity.Baseline;
import dk.project.mapper.response.BaselineResponseMapper;
import dk.project.service.internal.EntityManagerService;

public class BaselineController extends CRUDController<Baseline> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public BaselineController(EntityManagerService<Baseline> service) {
        super(service, Baseline.class, BaselineResponseMapper::toDTO);
    }

}