package dk.project.route.impl;

import dk.project.controller.impl.BaselineController;
import dk.project.entity.Baseline;
import dk.project.service.internal.BaselineService;
import jakarta.persistence.EntityManagerFactory;

public class BaselineRouting extends CRUDRouting<Baseline> {

    // _________________________________________________________________________________________________________________

    public BaselineRouting(EntityManagerFactory emf) {
        super("/baseline", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static BaselineController createController(EntityManagerFactory emf) {
        return new BaselineController(new BaselineService(emf.createEntityManager()));
    }

}