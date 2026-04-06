package dk.project.route.impl;

import dk.project.controller.impl.BaselineIndividualController;
import dk.project.entity.BaselineIndividual;
import dk.project.service.internal.BaselineIndividualService;
import jakarta.persistence.EntityManagerFactory;

public class BaselineIndividualRouting extends CRUDRouting<BaselineIndividual> {

    // _________________________________________________________________________________________________________________

    public BaselineIndividualRouting(EntityManagerFactory emf) {
        super("/baseline/individual", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static BaselineIndividualController createController(EntityManagerFactory emf) {
        return new BaselineIndividualController(new BaselineIndividualService(emf.createEntityManager()));
    }

}