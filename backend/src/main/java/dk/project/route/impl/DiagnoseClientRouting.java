package dk.project.route.impl;

import dk.project.controller.impl.DiagnoseClientController;
import dk.project.entity.DiagnoseClient;
import dk.project.service.internal.DiagnoseClientService;
import jakarta.persistence.EntityManagerFactory;

public class DiagnoseClientRouting extends CRUDRouting<DiagnoseClient> {

    // Attributes

    // _________________________________________________________________________________________________________________

    public DiagnoseClientRouting(EntityManagerFactory emf) {
        super("/diagnose/client", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static DiagnoseClientController createController(EntityManagerFactory emf) {
        return new DiagnoseClientController(new DiagnoseClientService(emf.createEntityManager()));
    }

}