package dk.project.server.route;

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

    public MedicationRouting(EntityManagerFactory emf){
        em = emf.createEntityManager();
        // medicationController = new MedicationController(em);
        // medicationClientController = new MedicationClientController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            // Medication
            path("/medication", () -> {


                // MedicationClient
                path("/client", () -> {


                });

            });
        };

    }

    // _______________________________________________________________________________

}