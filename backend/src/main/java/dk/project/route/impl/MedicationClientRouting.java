package dk.project.route.impl;

import dk.project.controller.impl.MedicationClientController;
import dk.project.entity.MedicationClient;
import dk.project.service.internal.MedicationClientService;
import jakarta.persistence.EntityManagerFactory;

public class MedicationClientRouting extends CRUDRouting<MedicationClient> {

    // _________________________________________________________________________________________________________________

    public MedicationClientRouting(EntityManagerFactory emf) {
        super("/medication/client", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static MedicationClientController createController(EntityManagerFactory emf) {
        return new MedicationClientController(new MedicationClientService(emf.createEntityManager()));
    }

}