package dk.project.route.impl;

import dk.project.controller.impl.DiagnoseController;
import dk.project.entity.Diagnose;
import dk.project.service.internal.DiagnoseService;
import jakarta.persistence.EntityManagerFactory;

public class DiagnoseRouting extends CRUDRouting<Diagnose> {

    // _________________________________________________________________________________________________________________

    public DiagnoseRouting(EntityManagerFactory emf) {
        super("/diagnose", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static DiagnoseController createController(EntityManagerFactory emf) {
        return new DiagnoseController(new DiagnoseService(emf.createEntityManager()));
    }

}