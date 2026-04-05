package dk.project.route.impl;

import dk.project.controller.auth.ApiController;
import dk.project.enums.AccessLevelEnum;
import dk.project.security.access.AccessValidator;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ApiRouting {

    // Attributes
    private final ApiController apiController;

    // _________________________________________________________________________________________________________________

    public ApiRouting(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        this.apiController = new ApiController(em);
    }

    // _________________________________________________________________________________________________________________

    public EndpointGroup routes() {

        return () -> {
            path("/access", () -> {

                // -------------------------------------------------------------------

                get("/{keyId}", AccessValidator.access(
                        apiController::getApiMeta,
                        AccessLevelEnum.JWT_AND_API
                ));

                // -------------------------------------------------------------------

                post("/create", AccessValidator.access(
                        apiController::createApiKey,
                        AccessLevelEnum.JWT
                ));

                // -------------------------------------------------------------------

                post("/validate", AccessValidator.access(
                        apiController::validateApiKey,
                        AccessLevelEnum.JWT
                ));

                // -------------------------------------------------------------------

                post("/refresh", AccessValidator.access(
                        apiController::refreshApiKey,
                        AccessLevelEnum.JWT_AND_API
                ));

                // -------------------------------------------------------------------

                delete("/delete", AccessValidator.access(
                        apiController::deleteApiKey,
                        AccessLevelEnum.JWT_AND_API
                ));


            });

        };

    }

}
