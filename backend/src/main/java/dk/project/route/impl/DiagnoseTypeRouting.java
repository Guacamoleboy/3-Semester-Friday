package dk.project.route.impl;

import dk.project.controller.impl.DiagnoseTypeController;
import dk.project.entity.DiagnoseType;
import dk.project.service.internal.DiagnoseTypeService;
import jakarta.persistence.EntityManagerFactory;

public class DiagnoseTypeRouting extends CRUDRouting<DiagnoseType> {

    // _________________________________________________________________________________________________________________

    public DiagnoseTypeRouting(EntityManagerFactory emf) {
        super("/diagnose/type", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static DiagnoseTypeController createController(EntityManagerFactory emf) {
        return new DiagnoseTypeController(new DiagnoseTypeService(emf.createEntityManager()));
    }

}