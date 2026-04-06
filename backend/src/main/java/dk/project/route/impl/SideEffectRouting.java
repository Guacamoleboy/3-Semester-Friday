package dk.project.route.impl;

import dk.project.controller.impl.SideEffectController;
import dk.project.entity.SideEffect;
import dk.project.service.internal.SideEffectService;
import jakarta.persistence.EntityManagerFactory;

public class SideEffectRouting extends CRUDRouting<SideEffect> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public SideEffectRouting(EntityManagerFactory emf) {
        super("/sideeffect", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static SideEffectController createController(EntityManagerFactory emf) {
        return new SideEffectController(new SideEffectService(emf.createEntityManager()));
    }

}