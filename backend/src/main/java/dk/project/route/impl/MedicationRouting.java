package dk.project.route.impl;

import dk.project.controller.impl.MedicationClientController;
import dk.project.controller.impl.MedicationController;
import dk.project.service.internal.MedicationClientService;
import dk.project.service.internal.MedicationService;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class MedicationRouting {

    // Attributes
    private final EntityManager em;
    private final MedicationController medicationController;
    private final MedicationClientController medicationClientController;

    // _________________________________________________________________________________________________________________

    public MedicationRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();

        MedicationService medicationService = new MedicationService(em);
        medicationController = new MedicationController(medicationService);

        MedicationClientService medicationClientService = new MedicationClientService(em);
        medicationClientController = new MedicationClientController(medicationClientService);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {

            // -------------------------------------------------------------------

            path("/medication", () -> {

                // -------------------------------------------------------------------

                get("/all", medicationController::getAll);

                // -------------------------------------------------------------------

                get("/{id}", medicationController::getById);

                // -------------------------------------------------------------------

                post("/", medicationController::create);

                // -------------------------------------------------------------------

                put("/{id}", medicationController::updateById);

                // -------------------------------------------------------------------

                delete("/{id}", medicationController::deleteById);

                // -------------------------------------------------------------------

                path("/client", () -> {

                    // -------------------------------------------------------------------

                    get("/all", medicationClientController::getAll);

                    // -------------------------------------------------------------------

                    get("/{id}", medicationClientController::getById);

                    // -------------------------------------------------------------------

                    post("/", medicationClientController::create);

                    // -------------------------------------------------------------------

                    put("/{id}", medicationClientController::updateById);

                    // -------------------------------------------------------------------

                    delete("/{id}", medicationClientController::deleteById);

                });

            });

        };

    }

}