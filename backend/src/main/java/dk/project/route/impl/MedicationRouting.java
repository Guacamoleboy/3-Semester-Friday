package dk.project.route.impl;

import dk.project.controller.impl.MedicationController;
import dk.project.entity.Medication;
import dk.project.service.internal.MedicationService;
import jakarta.persistence.EntityManagerFactory;

public class MedicationRouting extends CRUDRouting<Medication> {

    // _________________________________________________________________________________________________________________

    public MedicationRouting(EntityManagerFactory emf) {
        super("/medication", createController(emf));
    }

    // _________________________________________________________________________________________________________________

    private static MedicationController createController(EntityManagerFactory emf) {
        return new MedicationController(new MedicationService(emf.createEntityManager()));
    }

}