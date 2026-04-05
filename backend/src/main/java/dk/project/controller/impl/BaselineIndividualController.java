package dk.project.controller.impl;

import dk.project.entity.BaselineIndividual;
import dk.project.service.internal.EntityManagerService;

public class BaselineIndividualController extends CRUDController<BaselineIndividual> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public BaselineIndividualController(EntityManagerService<BaselineIndividual> service) {
        super(service, BaselineIndividual.class, BaselineIndividualResponseMapper::toDTO);
    }

}
