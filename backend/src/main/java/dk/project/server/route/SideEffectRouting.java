package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class SideEffectRouting {

    // Attributes
    private final EntityManager em;
    // private final SideEffectController sideEffectController;

    // _______________________________________________________________________________

    public SideEffectRouting(EntityManagerFactory emf){
        em = emf.createEntityManager();
        // sideEffectController = new SideEffectController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            path("/sideeffect", () -> {


            });
        };

    }

    // _______________________________________________________________________________

}