package dk.project.server.route;

import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.path;

public class AuthRouting {

    // Attributes
    private final EntityManager em;
    // private final AuthController authController;

    // _______________________________________________________________________________

    public AuthRouting(EntityManagerFactory emf){
        em = emf.createEntityManager();
        // authController = new AuthController(em);
    }

    // _______________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            path("/auth", () -> {


            });
        };

    }

    // _______________________________________________________________________________

}