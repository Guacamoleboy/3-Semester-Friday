package dk.project.controller.impl;

import dk.project.entity.SideEffect;
import dk.project.service.internal.EntityManagerService;

public class SideEffectController extends CRUDController<SideEffect> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public SideEffectController(EntityManagerService<SideEffect> service) {
        super(service, SideEffect.class, SideEffectResponseMapper::toDTO);
    }

}
