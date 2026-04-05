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
                        authController::login,
                        AccessLevelEnum.PUBLIC
                ));

                // -------------------------------------------------------------------

                post("/register", AccessValidator.access(
                        authController::register,
                        AccessLevelEnum.PUBLIC
                ));

                // -------------------------------------------------------------------

                get("/me", AccessValidator.access(
                        authController::me,
                        AccessLevelEnum.JWT
                ));

                // -------------------------------------------------------------------

                post("/refresh", AccessValidator.access(
                        authController::refresh,
                        AccessLevelEnum.JWT
                ));

            });

        };

    }

}