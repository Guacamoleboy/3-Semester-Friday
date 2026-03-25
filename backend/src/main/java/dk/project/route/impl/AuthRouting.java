package dk.project.route.impl;

import dk.project.controller.auth.AuthController;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class AuthRouting {

    // Attributes
    private final EntityManager em;
    private final AuthController authController;

    // _________________________________________________________________________________________________________________

    public AuthRouting(EntityManagerFactory emf){
        em = emf.createEntityManager();
        authController = new AuthController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            path("/auth", () -> {
                post("/login", authController::login);
                post("/register", authController::register);
                get("/me", authController::me);
                post("/refresh", authController::refresh);
            });
        };

    }

    // _________________________________________________________________________________________________________________

}