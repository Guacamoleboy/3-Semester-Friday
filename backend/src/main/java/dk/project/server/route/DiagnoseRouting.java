package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class DiagnoseRouting {

    // Attributes
    private final EntityManager em;
    // private final DiagnoseController diagnoseController;
    // private final DiagnoseClientController diagnoseClientController;

    // _______________________________________________________________________________

    public DiagnoseRouting(EntityManagerFactory emf){
        em = emf.createEntityManager();
        // diagnoseController = new DiagnoseController(em);
        // diagnoseClientController = new DiagnoseClientController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            // Diagnose
            path("/Diagnose", () -> {


                // DiagnoseClient
                path("/client", () -> {


                });

            });
        };

    }

    // _______________________________________________________________________________

}