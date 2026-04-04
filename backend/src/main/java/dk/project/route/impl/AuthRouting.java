package dk.project.route.impl;

import dk.project.controller.auth.AuthController;
import dk.project.enums.AccessLevelEnum;
import dk.project.security.access.AccessValidator;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class AuthRouting {

    // Attributes
    private final EntityManager em;
    private final AuthController authController;

    // _________________________________________________________________________________________________________________

    public AuthRouting(EntityManagerFactory emf) {
        em = emf.createEntityManager();
        authController = new AuthController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes(){

        return () -> {
            path("/auth", () -> {

                // -------------------------------------------------------------------

                post("/login", AccessValidator.access(
                        AccessLevelEnum.PUBLIC,
                        authController::login
                ));

                // -------------------------------------------------------------------

                post("/register", AccessValidator.access(
                        AccessLevelEnum.PUBLIC,
                        authController::register
                ));

                // -------------------------------------------------------------------

                get("/me", AccessValidator.access(
                        AccessLevelEnum.JWT,
                        authController::me
                ));

                // -------------------------------------------------------------------

                post("/refresh", AccessValidator.access(
                        AccessLevelEnum.JWT,
                        authController::refresh
                ));

            });

        };

    }

}