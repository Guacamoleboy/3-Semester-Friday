package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class BaselineRouting {

    // Attributes
    private final EntityManager em;
    // private final BaselineController baselineController;
    // private final BaselineIndividualController baselineIndividualController;

    // _______________________________________________________________________________

    public BaselineRouting(EntityManagerFactory emf){
        em = emf.createEntityManager();
        // baselineController = new BaselineController(em);
        // baselineIndividualController = new BaselineIndividualController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {

            // Baseline
            path("/baseline", () -> {


                // BaselineIndividual
                path("/individual", () -> {

                });

            });

        };

    }

    // _______________________________________________________________________________

}