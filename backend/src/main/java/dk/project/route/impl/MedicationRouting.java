package dk.project.route.impl;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class MedicationRouting {

    // Attributes
    private final EntityManager em;
    // private final MedicationController medicationController;
    // private final MedicationClientController medicationClientController;

    // _______________________________________________________________________________

    public MedicationRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        // medicationController = new MedicationController(em);
        // medicationClientController = new MedicationClientController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            // Medication
            path("/medication", () -> {
                // get("/all", medicationController::getAll);
                // get("/{id}", medicationController::getById);
                // post("/", medicationController::create);
                // put("/{id}", medicationController::updateById);
                // delete("/{id}", medicationController::deleteById);

                // Medication Client
                path("/client", () -> {
                    // get("/all", medicationClientController::getAll);
                    // get("/{id}", medicationClientController::getById);
                    // post("/", medicationClientController::create);
                    // put("/{id}", medicationClientController::updateById);
                    // delete("/{id}", medicationClientController::deleteById);
                });
            });
        };

    }

    // _______________________________________________________________________________

}